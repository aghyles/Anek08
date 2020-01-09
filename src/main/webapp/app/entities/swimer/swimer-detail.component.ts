import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISwimer } from 'app/shared/model/swimer.model';

@Component({
  selector: 'jhi-swimer-detail',
  templateUrl: './swimer-detail.component.html'
})
export class SwimerDetailComponent implements OnInit {
  swimer: ISwimer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ swimer }) => {
      this.swimer = swimer;
    });
  }

  previousState() {
    window.history.back();
  }
}
