import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDosage } from 'app/shared/model/dosage.model';

@Component({
  selector: 'jhi-dosage-detail',
  templateUrl: './dosage-detail.component.html'
})
export class DosageDetailComponent implements OnInit {
  dosage: IDosage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dosage }) => {
      this.dosage = dosage;
    });
  }

  previousState() {
    window.history.back();
  }
}
