import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestPhysique } from 'app/shared/model/test-physique.model';
import { TestPhysiqueService } from './test-physique.service';

@Component({
  templateUrl: './test-physique-delete-dialog.component.html'
})
export class TestPhysiqueDeleteDialogComponent {
  testPhysique: ITestPhysique;

  constructor(
    protected testPhysiqueService: TestPhysiqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.testPhysiqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'testPhysiqueListModification',
        content: 'Deleted an testPhysique'
      });
      this.activeModal.dismiss(true);
    });
  }
}
