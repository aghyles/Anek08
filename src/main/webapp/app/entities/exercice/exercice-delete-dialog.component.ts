import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExercice } from 'app/shared/model/exercice.model';
import { ExerciceService } from './exercice.service';

@Component({
  templateUrl: './exercice-delete-dialog.component.html'
})
export class ExerciceDeleteDialogComponent {
  exercice: IExercice;

  constructor(protected exerciceService: ExerciceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.exerciceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'exerciceListModification',
        content: 'Deleted an exercice'
      });
      this.activeModal.dismiss(true);
    });
  }
}
