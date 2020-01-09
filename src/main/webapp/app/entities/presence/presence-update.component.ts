import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPresence, Presence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { FicheSeanceService } from 'app/entities/fiche-seance/fiche-seance.service';

@Component({
  selector: 'jhi-presence-update',
  templateUrl: './presence-update.component.html'
})
export class PresenceUpdateComponent implements OnInit {
  isSaving: boolean;

  ficheseances: IFicheSeance[];

  editForm = this.fb.group({
    id: [],
    idNageurAbs: [],
    ficheSeance: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected presenceService: PresenceService,
    protected ficheSeanceService: FicheSeanceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ presence }) => {
      this.updateForm(presence);
    });
    this.ficheSeanceService
      .query()
      .subscribe(
        (res: HttpResponse<IFicheSeance[]>) => (this.ficheseances = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(presence: IPresence) {
    this.editForm.patchValue({
      id: presence.id,
      idNageurAbs: presence.idNageurAbs,
      ficheSeance: presence.ficheSeance
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const presence = this.createFromForm();
    if (presence.id !== undefined) {
      this.subscribeToSaveResponse(this.presenceService.update(presence));
    } else {
      this.subscribeToSaveResponse(this.presenceService.create(presence));
    }
  }

  private createFromForm(): IPresence {
    return {
      ...new Presence(),
      id: this.editForm.get(['id']).value,
      idNageurAbs: this.editForm.get(['idNageurAbs']).value,
      ficheSeance: this.editForm.get(['ficheSeance']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPresence>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackFicheSeanceById(index: number, item: IFicheSeance) {
    return item.id;
  }
}
