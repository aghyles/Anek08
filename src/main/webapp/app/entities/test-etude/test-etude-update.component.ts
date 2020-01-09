import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITestEtude, TestEtude } from 'app/shared/model/test-etude.model';
import { TestEtudeService } from './test-etude.service';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';

@Component({
  selector: 'jhi-test-etude-update',
  templateUrl: './test-etude-update.component.html'
})
export class TestEtudeUpdateComponent implements OnInit {
  isSaving: boolean;

  fichetests: IFicheTest[];

  editForm = this.fb.group({
    id: [],
    testTitle: [],
    levelStudy: [],
    average: [],
    ficheTest: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected testEtudeService: TestEtudeService,
    protected ficheTestService: FicheTestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ testEtude }) => {
      this.updateForm(testEtude);
    });
    this.ficheTestService
      .query()
      .subscribe((res: HttpResponse<IFicheTest[]>) => (this.fichetests = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(testEtude: ITestEtude) {
    this.editForm.patchValue({
      id: testEtude.id,
      testTitle: testEtude.testTitle,
      levelStudy: testEtude.levelStudy,
      average: testEtude.average,
      ficheTest: testEtude.ficheTest
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const testEtude = this.createFromForm();
    if (testEtude.id !== undefined) {
      this.subscribeToSaveResponse(this.testEtudeService.update(testEtude));
    } else {
      this.subscribeToSaveResponse(this.testEtudeService.create(testEtude));
    }
  }

  private createFromForm(): ITestEtude {
    return {
      ...new TestEtude(),
      id: this.editForm.get(['id']).value,
      testTitle: this.editForm.get(['testTitle']).value,
      levelStudy: this.editForm.get(['levelStudy']).value,
      average: this.editForm.get(['average']).value,
      ficheTest: this.editForm.get(['ficheTest']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestEtude>>) {
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
