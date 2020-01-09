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
import { IDocument, Document } from 'app/shared/model/document.model';
import { DocumentService } from './document.service';
import { ISwimer } from 'app/shared/model/swimer.model';
import { SwimerService } from 'app/entities/swimer/swimer.service';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';
import { IExercice } from 'app/shared/model/exercice.model';
import { ExerciceService } from 'app/entities/exercice/exercice.service';

@Component({
  selector: 'jhi-document-update',
  templateUrl: './document-update.component.html'
})
export class DocumentUpdateComponent implements OnInit {
  isSaving: boolean;

  swimers: ISwimer[];

  fichetests: IFicheTest[];

  exercices: IExercice[];

  editForm = this.fb.group({
    id: [],
    docTitle: [null, [Validators.required]],
    dateDoc: [],
    linkDoc: [],
    typeDoc: [null, [Validators.required]],
    swimer: [],
    ficheTest: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected documentService: DocumentService,
    protected swimerService: SwimerService,
    protected ficheTestService: FicheTestService,
    protected exerciceService: ExerciceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ document }) => {
      this.updateForm(document);
    });
    this.swimerService
      .query()
      .subscribe((res: HttpResponse<ISwimer[]>) => (this.swimers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.ficheTestService
      .query()
      .subscribe((res: HttpResponse<IFicheTest[]>) => (this.fichetests = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.exerciceService
      .query()
      .subscribe((res: HttpResponse<IExercice[]>) => (this.exercices = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(document: IDocument) {
    this.editForm.patchValue({
      id: document.id,
      docTitle: document.docTitle,
      dateDoc: document.dateDoc != null ? document.dateDoc.format(DATE_TIME_FORMAT) : null,
      linkDoc: document.linkDoc,
      typeDoc: document.typeDoc,
      swimer: document.swimer,
      ficheTest: document.ficheTest
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const document = this.createFromForm();
    if (document.id !== undefined) {
      this.subscribeToSaveResponse(this.documentService.update(document));
    } else {
      this.subscribeToSaveResponse(this.documentService.create(document));
    }
  }

  private createFromForm(): IDocument {
    return {
      ...new Document(),
      id: this.editForm.get(['id']).value,
      docTitle: this.editForm.get(['docTitle']).value,
      dateDoc: this.editForm.get(['dateDoc']).value != null ? moment(this.editForm.get(['dateDoc']).value, DATE_TIME_FORMAT) : undefined,
      linkDoc: this.editForm.get(['linkDoc']).value,
      typeDoc: this.editForm.get(['typeDoc']).value,
      swimer: this.editForm.get(['swimer']).value,
      ficheTest: this.editForm.get(['ficheTest']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocument>>) {
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

  trackFicheTestById(index: number, item: IFicheTest) {
    return item.id;
  }

  trackExerciceById(index: number, item: IExercice) {
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
