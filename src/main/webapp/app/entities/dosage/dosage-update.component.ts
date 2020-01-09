import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDosage, Dosage } from 'app/shared/model/dosage.model';
import { DosageService } from './dosage.service';

@Component({
  selector: 'jhi-dosage-update',
  templateUrl: './dosage-update.component.html'
})
export class DosageUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    dosageS: [null, [Validators.required]],
    dosagelong: [null, [Validators.required]]
  });

  constructor(protected dosageService: DosageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dosage }) => {
      this.updateForm(dosage);
    });
  }

  updateForm(dosage: IDosage) {
    this.editForm.patchValue({
      id: dosage.id,
      dosageS: dosage.dosageS,
      dosagelong: dosage.dosagelong
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dosage = this.createFromForm();
    if (dosage.id !== undefined) {
      this.subscribeToSaveResponse(this.dosageService.update(dosage));
    } else {
      this.subscribeToSaveResponse(this.dosageService.create(dosage));
    }
  }

  private createFromForm(): IDosage {
    return {
      ...new Dosage(),
      id: this.editForm.get(['id']).value,
      dosageS: this.editForm.get(['dosageS']).value,
      dosagelong: this.editForm.get(['dosagelong']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDosage>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
