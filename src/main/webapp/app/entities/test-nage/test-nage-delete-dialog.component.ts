import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestNage } from 'app/shared/model/test-nage.model';
import { TestNageService } from './test-nage.service';

@Component({
  templateUrl: './test-nage-delete-dialog.component.html'
})
export class TestNageDeleteDialogComponent {
  testNage: ITestNage;

  constructor(protected testNageService: TestNageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.testNageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'testNageListModification',
        content: 'Deleted an testNage'
      });
      this.activeModal.dismiss(true);
    });
  }
}
