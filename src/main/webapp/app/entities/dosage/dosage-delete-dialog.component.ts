import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDosage } from 'app/shared/model/dosage.model';
import { DosageService } from './dosage.service';

@Component({
  templateUrl: './dosage-delete-dialog.component.html'
})
export class DosageDeleteDialogComponent {
  dosage: IDosage;

  constructor(protected dosageService: DosageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dosageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dosageListModification',
        content: 'Deleted an dosage'
      });
      this.activeModal.dismiss(true);
    });
  }
}
