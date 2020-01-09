import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFicheTest } from 'app/shared/model/fiche-test.model';

@Component({
  selector: 'jhi-fiche-test-detail',
  templateUrl: './fiche-test-detail.component.html'
})
export class FicheTestDetailComponent implements OnInit {
  ficheTest: IFicheTest;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ ficheTest }) => {
      this.ficheTest = ficheTest;
    });
  }

  previousState() {
    window.history.back();
  }
}
