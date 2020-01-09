import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGroupe, Groupe } from 'app/shared/model/groupe.model';
import { GroupeService } from 'app/entities/groupe/groupe.service';

@Component({
  selector: 'jhi-entityui-update',
  templateUrl: './entityUi-update.component.html'
})
export class EntityUiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupeName: [null, [Validators.required]],
    startDate: [null, [Validators.required]],
    days: [null, [Validators.required]],
    endDate: [],
    saison: [],
    category: [],
    obs: []
  });

  constructor(protected groupeService: GroupeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ groupe }) => {
      this.updateForm(groupe);
    });
  }

  updateForm(groupe: IGroupe) {
    this.editForm.patchValue({
      id: groupe.id,
      groupeName: groupe.groupeName,
      startDate: groupe.startDate != null ? groupe.startDate.format(DATE_TIME_FORMAT) : null,
      days: groupe.days,
      endDate: groupe.endDate != null ? groupe.endDate.format(DATE_TIME_FORMAT) : null,
      saison: groupe.saison,
      category: groupe.category,
      obs: groupe.obs
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const groupe = this.createFromForm();
    if (groupe.id !== undefined) {
      this.subscribeToSaveResponse(this.groupeService.update(groupe));
    } else {
      this.subscribeToSaveResponse(this.groupeService.create(groupe));
    }
  }

  private createFromForm(): IGroupe {
    return {
      ...new Groupe(),
      id: this.editForm.get(['id']).value,
      groupeName: this.editForm.get(['groupeName']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      days: this.editForm.get(['days']).value,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      saison: this.editForm.get(['saison']).value,
      category: this.editForm.get(['category']).value,
      obs: this.editForm.get(['obs']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupe>>) {
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
