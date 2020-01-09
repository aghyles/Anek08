import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFicheSeance } from 'app/shared/model/fiche-seance.model';

@Component({
  selector: 'jhi-fiche-seance-detail',
  templateUrl: './fiche-seance-detail.component.html'
})
export class FicheSeanceDetailComponent implements OnInit {
  ficheSeance: IFicheSeance;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ ficheSeance }) => {
      this.ficheSeance = ficheSeance;
    });
  }

  previousState() {
    window.history.back();
  }
}
