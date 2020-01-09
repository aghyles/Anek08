import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IExercice, Exercice } from 'app/shared/model/exercice.model';
import { ExerciceService } from './exercice.service';
import { IDosage } from 'app/shared/model/dosage.model';
import { DosageService } from 'app/entities/dosage/dosage.service';
import { IDocument } from 'app/shared/model/document.model';
import { DocumentService } from 'app/entities/document/document.service';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { FicheSeanceService } from 'app/entities/fiche-seance/fiche-seance.service';
import { IObjectif } from 'app/shared/model/objectif.model';
import { ObjectifService } from 'app/entities/objectif/objectif.service';

@Component({
  selector: 'jhi-exercice-update',
  templateUrl: './exercice-update.component.html'
})
export class ExerciceUpdateComponent implements OnInit {
  isSaving: boolean;

  dosages: IDosage[];

  documents: IDocument[];

  ficheseances: IFicheSeance[];

  objectifs: IObjectif[];

  editForm = this.fb.group({
    id: [],
    exerciceName: [null, [Validators.required]],
    recomendation: [],
    docSchema: [],
    dosage: [],
    documents: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected exerciceService: ExerciceService,
    protected dosageService: DosageService,
    protected documentService: DocumentService,
    protected ficheSeanceService: FicheSeanceService,
    protected objectifService: ObjectifService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ exercice }) => {
      this.updateForm(exercice);
    });
    this.dosageService
      .query()
      .subscribe((res: HttpResponse<IDosage[]>) => (this.dosages = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.documentService
      .query()
      .subscribe((res: HttpResponse<IDocument[]>) => (this.documents = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.ficheSeanceService
      .query()
      .subscribe(
        (res: HttpResponse<IFicheSeance[]>) => (this.ficheseances = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.objectifService
      .query()
      .subscribe((res: HttpResponse<IObjectif[]>) => (this.objectifs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(exercice: IExercice) {
    this.editForm.patchValue({
      id: exercice.id,
      exerciceName: exercice.exerciceName,
      recomendation: exercice.recomendation,
      docSchema: exercice.docSchema,
      dosage: exercice.dosage,
      documents: exercice.documents
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const exercice = this.createFromForm();
    if (exercice.id !== undefined) {
      this.subscribeToSaveResponse(this.exerciceService.update(exercice));
    } else {
      this.subscribeToSaveResponse(this.exerciceService.create(exercice));
    }
  }

  private createFromForm(): IExercice {
    return {
      ...new Exercice(),
      id: this.editForm.get(['id']).value,
      exerciceName: this.editForm.get(['exerciceName']).value,
      recomendation: this.editForm.get(['recomendation']).value,
      docSchema: this.editForm.get(['docSchema']).value,
      dosage: this.editForm.get(['dosage']).value,
      documents: this.editForm.get(['documents']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExercice>>) {
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

  trackDosageById(index: number, item: IDosage) {
    return item.id;
  }

  trackDocumentById(index: number, item: IDocument) {
    return item.id;
  }

  trackFicheSeanceById(index: number, item: IFicheSeance) {
    return item.id;
  }

  trackObjectifById(index: number, item: IObjectif) {
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
