import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITestNage, TestNage } from 'app/shared/model/test-nage.model';
import { TestNageService } from './test-nage.service';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';

@Component({
  selector: 'jhi-test-nage-update',
  templateUrl: './test-nage-update.component.html'
})
export class TestNageUpdateComponent implements OnInit {
  isSaving: boolean;

  fichetests: IFicheTest[];

  editForm = this.fb.group({
    id: [],
    testTitle: [],
    nl50: [],
    nl100: [],
    nl200: [],
    nl400: [],
    nl800: [],
    nl1500: [],
    pap50: [],
    pap100: [],
    pap200: [],
    dos50: [],
    dos100: [],
    dos200: [],
    brasse50: [],
    brasse100: [],
    brasse200: [],
    na4ge100: [],
    na4ge200: [],
    na4ge400: [],
    h1nl: [],
    autre: [],
    ficheTest: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected testNageService: TestNageService,
    protected ficheTestService: FicheTestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ testNage }) => {
      this.updateForm(testNage);
    });
    this.ficheTestService
      .query()
      .subscribe((res: HttpResponse<IFicheTest[]>) => (this.fichetests = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(testNage: ITestNage) {
    this.editForm.patchValue({
      id: testNage.id,
      testTitle: testNage.testTitle,
      nl50: testNage.nl50,
      nl100: testNage.nl100,
      nl200: testNage.nl200,
      nl400: testNage.nl400,
      nl800: testNage.nl800,
      nl1500: testNage.nl1500,
      pap50: testNage.pap50,
      pap100: testNage.pap100,
      pap200: testNage.pap200,
      dos50: testNage.dos50,
      dos100: testNage.dos100,
      dos200: testNage.dos200,
      brasse50: testNage.brasse50,
      brasse100: testNage.brasse100,
      brasse200: testNage.brasse200,
      na4ge100: testNage.na4ge100,
      na4ge200: testNage.na4ge200,
      na4ge400: testNage.na4ge400,
      h1nl: testNage.h1nl,
      autre: testNage.autre,
      ficheTest: testNage.ficheTest
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const testNage = this.createFromForm();
    if (testNage.id !== undefined) {
      this.subscribeToSaveResponse(this.testNageService.update(testNage));
    } else {
      this.subscribeToSaveResponse(this.testNageService.create(testNage));
    }
  }

  private createFromForm(): ITestNage {
    return {
      ...new TestNage(),
      id: this.editForm.get(['id']).value,
      testTitle: this.editForm.get(['testTitle']).value,
      nl50: this.editForm.get(['nl50']).value,
      nl100: this.editForm.get(['nl100']).value,
      nl200: this.editForm.get(['nl200']).value,
      nl400: this.editForm.get(['nl400']).value,
      nl800: this.editForm.get(['nl800']).value,
      nl1500: this.editForm.get(['nl1500']).value,
      pap50: this.editForm.get(['pap50']).value,
      pap100: this.editForm.get(['pap100']).value,
      pap200: this.editForm.get(['pap200']).value,
      dos50: this.editForm.get(['dos50']).value,
      dos100: this.editForm.get(['dos100']).value,
      dos200: this.editForm.get(['dos200']).value,
      brasse50: this.editForm.get(['brasse50']).value,
      brasse100: this.editForm.get(['brasse100']).value,
      brasse200: this.editForm.get(['brasse200']).value,
      na4ge100: this.editForm.get(['na4ge100']).value,
      na4ge200: this.editForm.get(['na4ge200']).value,
      na4ge400: this.editForm.get(['na4ge400']).value,
      h1nl: this.editForm.get(['h1nl']).value,
      autre: this.editForm.get(['autre']).value,
      ficheTest: this.editForm.get(['ficheTest']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestNage>>) {
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

  trackFicheTestById(index: number, item: IFicheTest) {
    return item.id;
  }
}
