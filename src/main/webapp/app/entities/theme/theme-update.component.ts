import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITheme, Theme } from 'app/shared/model/theme.model';
import { ThemeService } from './theme.service';
import { IObjectif } from 'app/shared/model/objectif.model';
import { ObjectifService } from 'app/entities/objectif/objectif.service';

@Component({
  selector: 'jhi-theme-update',
  templateUrl: './theme-update.component.html'
})
export class ThemeUpdateComponent implements OnInit {
  isSaving: boolean;

  objectifs: IObjectif[];

  editForm = this.fb.group({
    id: [],
    themeName: [null, [Validators.required]],
    description: [],
    objectifs: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected themeService: ThemeService,
    protected objectifService: ObjectifService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ theme }) => {
      this.updateForm(theme);
    });
    this.objectifService
      .query()
      .subscribe((res: HttpResponse<IObjectif[]>) => (this.objectifs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(theme: ITheme) {
    this.editForm.patchValue({
      id: theme.id,
      themeName: theme.themeName,
      description: theme.description,
      objectifs: theme.objectifs
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const theme = this.createFromForm();
    if (theme.id !== undefined) {
      this.subscribeToSaveResponse(this.themeService.update(theme));
    } else {
      this.subscribeToSaveResponse(this.themeService.create(theme));
    }
  }

  private createFromForm(): ITheme {
    return {
      ...new Theme(),
      id: this.editForm.get(['id']).value,
      themeName: this.editForm.get(['themeName']).value,
      description: this.editForm.get(['description']).value,
      objectifs: this.editForm.get(['objectifs']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITheme>>) {
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
