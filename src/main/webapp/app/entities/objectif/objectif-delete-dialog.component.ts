import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObjectif } from 'app/shared/model/objectif.model';
import { ObjectifService } from './objectif.service';

@Component({
  templateUrl: './objectif-delete-dialog.component.html'
})
export class ObjectifDeleteDialogComponent {
  objectif: IObjectif;

  constructor(protected objectifService: ObjectifService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.objectifService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'objectifListModification',
        content: 'Deleted an objectif'
      });
      this.activeModal.dismiss(true);
    });
  }
}
