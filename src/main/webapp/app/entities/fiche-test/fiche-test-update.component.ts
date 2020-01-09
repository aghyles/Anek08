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
import { IFicheTest, FicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from './fiche-test.service';
import { ISwimer } from 'app/shared/model/swimer.model';
import { SwimerService } from 'app/entities/swimer/swimer.service';

@Component({
  selector: 'jhi-fiche-test-update',
  templateUrl: './fiche-test-update.component.html'
})
export class FicheTestUpdateComponent implements OnInit {
  isSaving: boolean;

  swimers: ISwimer[];

  editForm = this.fb.group({
    id: [],
    typeTest: [null, [Validators.required]],
    datetest: [null, [Validators.required]],
    swimer: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ficheTestService: FicheTestService,
    protected swimerService: SwimerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ficheTest }) => {
      this.updateForm(ficheTest);
    });
    this.swimerService
      .query()
      .subscribe((res: HttpResponse<ISwimer[]>) => (this.swimers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ficheTest: IFicheTest) {
    this.editForm.patchValue({
      id: ficheTest.id,
      typeTest: ficheTest.typeTest,
      datetest: ficheTest.datetest != null ? ficheTest.datetest.format(DATE_TIME_FORMAT) : null,
      swimer: ficheTest.swimer
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const ficheTest = this.createFromForm();
    if (ficheTest.id !== undefined) {
      this.subscribeToSaveResponse(this.ficheTestService.update(ficheTest));
    } else {
      this.subscribeToSaveResponse(this.ficheTestService.create(ficheTest));
    }
  }

  private createFromForm(): IFicheTest {
    return {
      ...new FicheTest(),
      id: this.editForm.get(['id']).value,
      typeTest: this.editForm.get(['typeTest']).value,
      datetest: this.editForm.get(['datetest']).value != null ? moment(this.editForm.get(['datetest']).value, DATE_TIME_FORMAT) : undefined,
      swimer: this.editForm.get(['swimer']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheTest>>) {
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
