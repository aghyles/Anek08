import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestPhysique } from 'app/shared/model/test-physique.model';

@Component({
  selector: 'jhi-test-physique-detail',
  templateUrl: './test-physique-detail.component.html'
})
export class TestPhysiqueDetailComponent implements OnInit {
  testPhysique: ITestPhysique;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ testPhysique }) => {
      this.testPhysique = testPhysique;
    });
  }

  previousState() {
    window.history.back();
  }
}
