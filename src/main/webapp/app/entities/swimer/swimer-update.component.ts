import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISwimer, Swimer } from 'app/shared/model/swimer.model';
import { SwimerService } from './swimer.service';
import { IGroupe } from 'app/shared/model/groupe.model';
import { GroupeService } from 'app/entities/groupe/groupe.service';

@Component({
  selector: 'jhi-swimer-update',
  templateUrl: './swimer-update.component.html'
})
export class SwimerUpdateComponent implements OnInit {
  isSaving: boolean;

  groupes: IGroupe[];

  editForm = this.fb.group({
    id: [],
    licenceNum: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    sexe: [],
    bearthday: [],
    phoneNumber: [],
    eMail: [null, [Validators.pattern('^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$')]],
    address: [],
    studyTime: [],
    firstSwim: [],
    groupeName: [],
    document: [],
    groupe: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected swimerService: SwimerService,
    protected groupeService: GroupeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ swimer }) => {
      this.updateForm(swimer);
    });
    this.groupeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGroupe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGroupe[]>) => response.body)
      )
      .subscribe((res: IGroupe[]) => (this.groupes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(swimer: ISwimer) {
    this.editForm.patchValue({
      id: swimer.id,
      licenceNum: swimer.licenceNum,
      firstName: swimer.firstName,
      lastName: swimer.lastName,
      sexe: swimer.sexe,
      bearthday: swimer.bearthday != null ? swimer.bearthday.format(DATE_TIME_FORMAT) : null,
      phoneNumber: swimer.phoneNumber,
      eMail: swimer.eMail,
      address: swimer.address,
      studyTime: swimer.studyTime,
      firstSwim: swimer.firstSwim != null ? swimer.firstSwim.format(DATE_TIME_FORMAT) : null,
      groupeName: swimer.groupeName,
      document: swimer.document,
      groupe: swimer.groupe
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const swimer = this.createFromForm();
    if (swimer.id !== undefined) {
      this.subscribeToSaveResponse(this.swimerService.update(swimer));
    } else {
      this.subscribeToSaveResponse(this.swimerService.create(swimer));
    }
  }

  private createFromForm(): ISwimer {
    return {
      ...new Swimer(),
      id: this.editForm.get(['id']).value,
      licenceNum: this.editForm.get(['licenceNum']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      sexe: this.editForm.get(['sexe']).value,
      bearthday:
        this.editForm.get(['bearthday']).value != null ? moment(this.editForm.get(['bearthday']).value, DATE_TIME_FORMAT) : undefined,
      phoneNumber: this.editForm.get(['phoneNumber']).value,
      eMail: this.editForm.get(['eMail']).value,
      address: this.editForm.get(['address']).value,
      studyTime: this.editForm.get(['studyTime']).value,
      firstSwim:
        this.editForm.get(['firstSwim']).value != null ? moment(this.editForm.get(['firstSwim']).value, DATE_TIME_FORMAT) : undefined,
      groupeName: this.editForm.get(['groupeName']).value,
      document: this.editForm.get(['document']).value,
      groupe: this.editForm.get(['groupe']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISwimer>>) {
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

  trackGroupeById(index: number, item: IGroupe) {
    return item.id;
  }
}
