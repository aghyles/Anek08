import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IObjectif, Objectif } from 'app/shared/model/objectif.model';
import { ObjectifService } from './objectif.service';
import { IExercice } from 'app/shared/model/exercice.model';
import { ExerciceService } from 'app/entities/exercice/exercice.service';
import { ITheme } from 'app/shared/model/theme.model';
import { ThemeService } from 'app/entities/theme/theme.service';

@Component({
  selector: 'jhi-objectif-update',
  templateUrl: './objectif-update.component.html'
})
export class ObjectifUpdateComponent implements OnInit {
  isSaving: boolean;

  exercices: IExercice[];

  themes: ITheme[];

  editForm = this.fb.group({
    id: [],
    objectifName: [null, [Validators.required]],
    description: [],
    exercices: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected objectifService: ObjectifService,
    protected exerciceService: ExerciceService,
    protected themeService: ThemeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ objectif }) => {
      this.updateForm(objectif);
    });
    this.exerciceService
      .query()
      .subscribe((res: HttpResponse<IExercice[]>) => (this.exercices = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.themeService
      .query()
      .subscribe((res: HttpResponse<ITheme[]>) => (this.themes = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(objectif: IObjectif) {
    this.editForm.patchValue({
      id: objectif.id,
      objectifName: objectif.objectifName,
      description: objectif.description,
      exercices: objectif.exercices
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const objectif = this.createFromForm();
    if (objectif.id !== undefined) {
      this.subscribeToSaveResponse(this.objectifService.update(objectif));
    } else {
      this.subscribeToSaveResponse(this.objectifService.create(objectif));
    }
  }

  private createFromForm(): IObjectif {
    return {
      ...new Objectif(),
      id: this.editForm.get(['id']).value,
      objectifName: this.editForm.get(['objectifName']).value,
      description: this.editForm.get(['description']).value,
      exercices: this.editForm.get(['exercices']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObjectif>>) {
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

  trackThemeById(index: number, item: ITheme) {
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
