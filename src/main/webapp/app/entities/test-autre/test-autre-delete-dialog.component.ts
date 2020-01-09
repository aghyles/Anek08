import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestAutre } from 'app/shared/model/test-autre.model';
import { TestAutreService } from './test-autre.service';

@Component({
  templateUrl: './test-autre-delete-dialog.component.html'
})
export class TestAutreDeleteDialogComponent {
  testAutre: ITestAutre;

  constructor(protected testAutreService: TestAutreService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.testAutreService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'testAutreListModification',
        content: 'Deleted an testAutre'
      });
      this.activeModal.dismiss(true);
    });
  }
}
