import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObjectif } from 'app/shared/model/objectif.model';

@Component({
  selector: 'jhi-objectif-detail',
  templateUrl: './objectif-detail.component.html'
})
export class ObjectifDetailComponent implements OnInit {
  objectif: IObjectif;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ objectif }) => {
      this.objectif = objectif;
    });
  }

  previousState() {
    window.history.back();
  }
}
