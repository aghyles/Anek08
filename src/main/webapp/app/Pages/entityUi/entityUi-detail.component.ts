import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupe } from 'app/shared/model/groupe.model';

@Component({
  selector: 'jhi-entityui-detail',
  templateUrl: './entityUi-detail.component.html'
})
export class EntityUiDetailComponent implements OnInit {
  groupe: IGroupe;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupe }) => {
      this.groupe = groupe;
    });
  }

  previousState() {
    window.history.back();
  }
}
