import { Component, Inject, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CalendarEvent } from 'angular-calendar';

import { MatColors } from '@fuse/mat-colors';

import { CalendarEventModel } from 'app/calendar/event.model';
import { IExercice } from 'app/shared/model/exercice.model';
import { IGroupe } from 'app/shared/model/groupe.model';
import { JhiAlertService } from 'ng-jhipster';
import { FicheSeanceService } from 'app/entities/fiche-seance/fiche-seance.service';
import { ExerciceService } from 'app/entities/exercice/exercice.service';
import { GroupeService } from 'app/entities/groupe/groupe.service';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FicheSeance, IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-calendar-event-form-dialog',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class CalendarEventFormDialogComponent implements OnInit {
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

  action: string;
  event: CalendarEvent;
  eventForm: FormGroup;
  dialogTitle: string;
  presetColors = MatColors.presets;

  /**
   * Constructor
   *
   * @param {MatDialogRef<CalendarEventFormDialogComponent>} matDialogRef
   * @param _data
   * @param {FormBuilder} _formBuilder
   */
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ficheSeanceService: FicheSeanceService,
    protected exerciceService: ExerciceService,
    protected groupeService: GroupeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public matDialogRef: MatDialogRef<CalendarEventFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private _data: any,
    private _formBuilder: FormBuilder
  ) {
    this.event = _data.event;
    this.action = _data.action;

    if (this.action === 'edit') {
      this.dialogTitle = this.event.title;
    } else {
      this.dialogTitle = 'Fiche de Séance n°99 du 22/11/2019';
      this.event = new CalendarEventModel({
        start: _data.date,
        end: _data.date
      });
    }

    //   this.eventForm = this.createEventForm();
    this.eventForm = this.editForm;
  }

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

    // eslint-disable-next-line no-console
    console.log('ididididididididididididididididididididididididididididid==' + ficheSeance.id);

    if (ficheSeance.id !== undefined && ficheSeance.id !== null) {
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
    this.matDialogRef.close();
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

  // -----------------------------------------------------------------------------------------------------
  // @ Public methods
  // -----------------------------------------------------------------------------------------------------

  /**
   * Create the event form
   *
   * @returns {FormGroup}
   */
  createEventForm(): FormGroup {
    return new FormGroup({
      title: new FormControl(this.event.title),
      start: new FormControl(this.event.start),
      end: new FormControl(this.event.end),
      allDay: new FormControl(this.event.allDay),
      color: this._formBuilder.group({
        primary: new FormControl(this.event.color.primary),
        secondary: new FormControl(this.event.color.secondary)
      }),
      meta: this._formBuilder.group({
        location: new FormControl(this.event.meta.location),
        notes: new FormControl(this.event.meta.notes)
      })
    });
  }
}
