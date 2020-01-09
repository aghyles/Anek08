import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';
import { MesureAnthropoService } from './mesure-anthropo.service';

@Component({
  templateUrl: './mesure-anthropo-delete-dialog.component.html'
})
export class MesureAnthropoDeleteDialogComponent {
  mesureAnthropo: IMesureAnthropo;

  constructor(
    protected mesureAnthropoService: MesureAnthropoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mesureAnthropoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'mesureAnthropoListModification',
        content: 'Deleted an mesureAnthropo'
      });
      this.activeModal.dismiss(true);
    });
  }
}
