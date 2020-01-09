import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from './fiche-test.service';

@Component({
  templateUrl: './fiche-test-delete-dialog.component.html'
})
export class FicheTestDeleteDialogComponent {
  ficheTest: IFicheTest;

  constructor(protected ficheTestService: FicheTestService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.ficheTestService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'ficheTestListModification',
        content: 'Deleted an ficheTest'
      });
      this.activeModal.dismiss(true);
    });
  }
}
