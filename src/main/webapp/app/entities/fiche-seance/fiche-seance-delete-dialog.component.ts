import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { FicheSeanceService } from './fiche-seance.service';

@Component({
  templateUrl: './fiche-seance-delete-dialog.component.html'
})
export class FicheSeanceDeleteDialogComponent {
  ficheSeance: IFicheSeance;

  constructor(
    protected ficheSeanceService: FicheSeanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.ficheSeanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'ficheSeanceListModification',
        content: 'Deleted an ficheSeance'
      });
      this.activeModal.dismiss(true);
    });
  }
}
