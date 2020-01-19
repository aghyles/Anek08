import { Component, OnInit } from '@angular/core';
import { IGroupe } from 'app/shared/model/groupe.model';
import { FormBuilder, Validators } from '@angular/forms';
import { JhiAlertService } from 'ng-jhipster';
import { SwimerService } from 'app/entities/swimer/swimer.service';
import { GroupeService } from 'app/entities/groupe/groupe.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { ISwimer, Swimer } from 'app/shared/model/swimer.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss']
})
export class DashboardComponent implements OnInit {
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
  preReussi: boolean;
  preEchec: boolean;
  staticAlertClosed: boolean;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected swimerService: SwimerService,
    protected groupeService: GroupeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.preReussi = false;
    this.preEchec = false;
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ swimer }) => {
      this.updateForm(swimer);
    });
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
    this.ngOnInit();
    // eslint-disable-next-line no-console
    console.log('Event clicked zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz');
    if (swimer.id !== undefined && swimer.id !== null) {
      // eslint-disable-next-line no-console
      console.log('Event clicked ggggggggggggggggggggggggggggggg', swimer.id);
      this.subscribeToSaveResponse(this.swimerService.update(swimer));
    } else {
      const dateAgeMinimum = moment('2012-01-01 10:30:26.555');
      if (swimer.bearthday > dateAgeMinimum) {
        // eslint-disable-next-line no-console
        console.log('Event clicked aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
        this.subscribeToSaveResponse(this.swimerService.create(swimer));
        this.preReussi = true;
        // this.preEchec = false;
        // eslint-disable-next-line no-console
        console.log('Event clicked bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');
      } else {
        // this.preReussi = false;
        this.preEchec = true;
        // eslint-disable-next-line no-console
        console.log('Event clicked nonnnnnnnn');
        this.subscribeToSaveResponse(this.swimerService.create(swimer));
      }
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
    // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
    this.preReussi = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackGroupeById(index: number, item: IGroupe) {
    return item.id;
  }
}
