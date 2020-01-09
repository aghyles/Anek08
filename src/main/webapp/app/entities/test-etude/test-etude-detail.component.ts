import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestEtude } from 'app/shared/model/test-etude.model';

@Component({
  selector: 'jhi-test-etude-detail',
  templateUrl: './test-etude-detail.component.html'
})
export class TestEtudeDetailComponent implements OnInit {
  testEtude: ITestEtude;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ testEtude }) => {
      this.testEtude = testEtude;
    });
  }

  previousState() {
    window.history.back();
  }
}
