import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPresence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';

@Component({
  templateUrl: './presence-delete-dialog.component.html'
})
export class PresenceDeleteDialogComponent {
  presence: IPresence;

  constructor(protected presenceService: PresenceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.presenceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'presenceListModification',
        content: 'Deleted an presence'
      });
      this.activeModal.dismiss(true);
    });
  }
}
