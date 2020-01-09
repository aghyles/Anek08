import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

@Component({
  selector: 'jhi-mesure-anthropo-detail',
  templateUrl: './mesure-anthropo-detail.component.html'
})
export class MesureAnthropoDetailComponent implements OnInit {
  mesureAnthropo: IMesureAnthropo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mesureAnthropo }) => {
      this.mesureAnthropo = mesureAnthropo;
    });
  }

  previousState() {
    window.history.back();
  }
}
