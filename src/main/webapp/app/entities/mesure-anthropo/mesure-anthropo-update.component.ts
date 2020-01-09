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
import { IMesureAnthropo, MesureAnthropo } from 'app/shared/model/mesure-anthropo.model';
import { MesureAnthropoService } from './mesure-anthropo.service';
import { ISwimer } from 'app/shared/model/swimer.model';
import { SwimerService } from 'app/entities/swimer/swimer.service';

@Component({
  selector: 'jhi-mesure-anthropo-update',
  templateUrl: './mesure-anthropo-update.component.html'
})
export class MesureAnthropoUpdateComponent implements OnInit {
  isSaving: boolean;

  swimers: ISwimer[];

  editForm = this.fb.group({
    id: [],
    date: [null, [Validators.required]],
    poids: [],
    stature: [],
    tailleAssis: [],
    longTronc: [],
    longMembreInferieurs: [],
    longMembreSuperieur: [],
    anvergureBras: [],
    longBras: [],
    longJambes: [],
    longPieds: [],
    hauteurPied: [],
    longMain: [],
    diamMain: [],
    diamBiacromial: [],
    diamBicretal: [],
    diamThorax: [],
    circThorax: [],
    circThoraxInspirant: [],
    circThoraxExpirant: [],
    circBrasContracte: [],
    circBrasDecontract: [],
    circCuisse: [],
    swimer: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mesureAnthropoService: MesureAnthropoService,
    protected swimerService: SwimerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mesureAnthropo }) => {
      this.updateForm(mesureAnthropo);
    });
    this.swimerService
      .query()
      .subscribe((res: HttpResponse<ISwimer[]>) => (this.swimers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mesureAnthropo: IMesureAnthropo) {
    this.editForm.patchValue({
      id: mesureAnthropo.id,
      date: mesureAnthropo.date != null ? mesureAnthropo.date.format(DATE_TIME_FORMAT) : null,
      poids: mesureAnthropo.poids,
      stature: mesureAnthropo.stature,
      tailleAssis: mesureAnthropo.tailleAssis,
      longTronc: mesureAnthropo.longTronc,
      longMembreInferieurs: mesureAnthropo.longMembreInferieurs,
      longMembreSuperieur: mesureAnthropo.longMembreSuperieur,
      anvergureBras: mesureAnthropo.anvergureBras,
      longBras: mesureAnthropo.longBras,
      longJambes: mesureAnthropo.longJambes,
      longPieds: mesureAnthropo.longPieds,
      hauteurPied: mesureAnthropo.hauteurPied,
      longMain: mesureAnthropo.longMain,
      diamMain: mesureAnthropo.diamMain,
      diamBiacromial: mesureAnthropo.diamBiacromial,
      diamBicretal: mesureAnthropo.diamBicretal,
      diamThorax: mesureAnthropo.diamThorax,
      circThorax: mesureAnthropo.circThorax,
      circThoraxInspirant: mesureAnthropo.circThoraxInspirant,
      circThoraxExpirant: mesureAnthropo.circThoraxExpirant,
      circBrasContracte: mesureAnthropo.circBrasContracte,
      circBrasDecontract: mesureAnthropo.circBrasDecontract,
      circCuisse: mesureAnthropo.circCuisse,
      swimer: mesureAnthropo.swimer
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mesureAnthropo = this.createFromForm();
    if (mesureAnthropo.id !== undefined) {
      this.subscribeToSaveResponse(this.mesureAnthropoService.update(mesureAnthropo));
    } else {
      this.subscribeToSaveResponse(this.mesureAnthropoService.create(mesureAnthropo));
    }
  }

  private createFromForm(): IMesureAnthropo {
    return {
      ...new MesureAnthropo(),
      id: this.editForm.get(['id']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      poids: this.editForm.get(['poids']).value,
      stature: this.editForm.get(['stature']).value,
      tailleAssis: this.editForm.get(['tailleAssis']).value,
      longTronc: this.editForm.get(['longTronc']).value,
      longMembreInferieurs: this.editForm.get(['longMembreInferieurs']).value,
      longMembreSuperieur: this.editForm.get(['longMembreSuperieur']).value,
      anvergureBras: this.editForm.get(['anvergureBras']).value,
      longBras: this.editForm.get(['longBras']).value,
      longJambes: this.editForm.get(['longJambes']).value,
      longPieds: this.editForm.get(['longPieds']).value,
      hauteurPied: this.editForm.get(['hauteurPied']).value,
      longMain: this.editForm.get(['longMain']).value,
      diamMain: this.editForm.get(['diamMain']).value,
      diamBiacromial: this.editForm.get(['diamBiacromial']).value,
      diamBicretal: this.editForm.get(['diamBicretal']).value,
      diamThorax: this.editForm.get(['diamThorax']).value,
      circThorax: this.editForm.get(['circThorax']).value,
      circThoraxInspirant: this.editForm.get(['circThoraxInspirant']).value,
      circThoraxExpirant: this.editForm.get(['circThoraxExpirant']).value,
      circBrasContracte: this.editForm.get(['circBrasContracte']).value,
      circBrasDecontract: this.editForm.get(['circBrasDecontract']).value,
      circCuisse: this.editForm.get(['circCuisse']).value,
      swimer: this.editForm.get(['swimer']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMesureAnthropo>>) {
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

  trackSwimerById(index: number, item: ISwimer) {
    return item.id;
  }
}
