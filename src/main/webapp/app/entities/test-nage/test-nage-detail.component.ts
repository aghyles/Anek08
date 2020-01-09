import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestNage } from 'app/shared/model/test-nage.model';

@Component({
  selector: 'jhi-test-nage-detail',
  templateUrl: './test-nage-detail.component.html'
})
export class TestNageDetailComponent implements OnInit {
  testNage: ITestNage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ testNage }) => {
      this.testNage = testNage;
    });
  }

  previousState() {
    window.history.back();
  }
}
