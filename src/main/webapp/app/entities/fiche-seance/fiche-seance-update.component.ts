import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IFicheSeance, FicheSeance } from 'app/shared/model/fiche-seance.model';
import { FicheSeanceService } from './fiche-seance.service';
import { IExercice } from 'app/shared/model/exercice.model';
import { ExerciceService } from 'app/entities/exercice/exercice.service';
import { IGroupe } from 'app/shared/model/groupe.model';
import { GroupeService } from 'app/entities/groupe/groupe.service';

@Component({
  selector: 'jhi-fiche-seance-update',
  templateUrl: './fiche-seance-update.component.html'
})
export class FicheSeanceUpdateComponent implements OnInit {
  isSaving: boolean;

  exercices: IExercice[];

  groupes: IGroupe[];

  editForm = this.fb.group({
    id: [],
    ficheSNum: [null, [Validators.required]],
    date: [null, [Validators.required]],
    observation: [],
    volume: [],
    bilan: [],
    exercices: [],
    groupe: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ficheSeanceService: FicheSeanceService,
    protected exerciceService: ExerciceService,
    protected groupeService: GroupeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ficheSeance }) => {
      this.updateForm(ficheSeance);
    });
    this.exerciceService
      .query()
      .subscribe((res: HttpResponse<IExercice[]>) => (this.exercices = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.groupeService
      .query()
      .subscribe((res: HttpResponse<IGroupe[]>) => (this.groupes = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ficheSeance: IFicheSeance) {
    this.editForm.patchValue({
      id: ficheSeance.id,
      ficheSNum: ficheSeance.ficheSNum,
      date: ficheSeance.date != null ? ficheSeance.date.format(DATE_TIME_FORMAT) : null,
      observation: ficheSeance.observation,
      volume: ficheSeance.volume,
      bilan: ficheSeance.bilan,
      exercices: ficheSeance.exercices,
      groupe: ficheSeance.groupe
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const ficheSeance = this.createFromForm();
    if (ficheSeance.id !== undefined) {
      this.subscribeToSaveResponse(this.ficheSeanceService.update(ficheSeance));
    } else {
      this.subscribeToSaveResponse(this.ficheSeanceService.create(ficheSeance));
    }
  }

  private createFromForm(): IFicheSeance {
    return {
      ...new FicheSeance(),
      id: this.editForm.get(['id']).value,
      ficheSNum: this.editForm.get(['ficheSNum']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      observation: this.editForm.get(['observation']).value,
      volume: this.editForm.get(['volume']).value,
      bilan: this.editForm.get(['bilan']).value,
      exercices: this.editForm.get(['exercices']).value,
      groupe: this.editForm.get(['groupe']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheSeance>>) {
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

  trackExerciceById(index: number, item: IExercice) {
    return item.id;
  }

  trackGroupeById(index: number, item: IGroupe) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
