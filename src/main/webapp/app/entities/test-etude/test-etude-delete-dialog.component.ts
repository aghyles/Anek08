import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestEtude } from 'app/shared/model/test-etude.model';
import { TestEtudeService } from './test-etude.service';

@Component({
  templateUrl: './test-etude-delete-dialog.component.html'
})
export class TestEtudeDeleteDialogComponent {
  testEtude: ITestEtude;

  constructor(protected testEtudeService: TestEtudeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.testEtudeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'testEtudeListModification',
        content: 'Deleted an testEtude'
      });
      this.activeModal.dismiss(true);
    });
  }
}
