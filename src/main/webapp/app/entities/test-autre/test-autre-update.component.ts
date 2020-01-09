import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITestAutre, TestAutre } from 'app/shared/model/test-autre.model';
import { TestAutreService } from './test-autre.service';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';

@Component({
  selector: 'jhi-test-autre-update',
  templateUrl: './test-autre-update.component.html'
})
export class TestAutreUpdateComponent implements OnInit {
  isSaving: boolean;

  fichetests: IFicheTest[];

  editForm = this.fb.group({
    id: [],
    testTitle: [],
    obs1: [],
    obs2: [],
    obs3: [],
    obs4: [],
    obs5: [],
    obs6: [],
    obs7: [],
    ficheTest: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected testAutreService: TestAutreService,
    protected ficheTestService: FicheTestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ testAutre }) => {
      this.updateForm(testAutre);
    });
    this.ficheTestService
      .query()
      .subscribe((res: HttpResponse<IFicheTest[]>) => (this.fichetests = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(testAutre: ITestAutre) {
    this.editForm.patchValue({
      id: testAutre.id,
      testTitle: testAutre.testTitle,
      obs1: testAutre.obs1,
      obs2: testAutre.obs2,
      obs3: testAutre.obs3,
      obs4: testAutre.obs4,
      obs5: testAutre.obs5,
      obs6: testAutre.obs6,
      obs7: testAutre.obs7,
      ficheTest: testAutre.ficheTest
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const testAutre = this.createFromForm();
    if (testAutre.id !== undefined) {
      this.subscribeToSaveResponse(this.testAutreService.update(testAutre));
    } else {
      this.subscribeToSaveResponse(this.testAutreService.create(testAutre));
    }
  }

  private createFromForm(): ITestAutre {
    return {
      ...new TestAutre(),
      id: this.editForm.get(['id']).value,
      testTitle: this.editForm.get(['testTitle']).value,
      obs1: this.editForm.get(['obs1']).value,
      obs2: this.editForm.get(['obs2']).value,
      obs3: this.editForm.get(['obs3']).value,
      obs4: this.editForm.get(['obs4']).value,
      obs5: this.editForm.get(['obs5']).value,
      obs6: this.editForm.get(['obs6']).value,
      obs7: this.editForm.get(['obs7']).value,
      ficheTest: this.editForm.get(['ficheTest']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestAutre>>) {
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
