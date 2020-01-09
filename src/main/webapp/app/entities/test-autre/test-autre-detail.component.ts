import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestAutre } from 'app/shared/model/test-autre.model';

@Component({
  selector: 'jhi-test-autre-detail',
  templateUrl: './test-autre-detail.component.html'
})
export class TestAutreDetailComponent implements OnInit {
  testAutre: ITestAutre;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ testAutre }) => {
      this.testAutre = testAutre;
    });
  }

  previousState() {
    window.history.back();
  }
}
