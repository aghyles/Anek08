import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISwimer } from 'app/shared/model/swimer.model';
import { SwimerService } from './swimer.service';

@Component({
  selector: 'jhi-swimer-delete-dialog',
  templateUrl: './swimer-delete-dialog.component.html'
})
export class SwimerDeleteDialogComponent {
  swimer: ISwimer;

  constructor(protected swimerService: SwimerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.swimerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'swimerListModification',
        content: 'Deleted an swimer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-swimer-delete-popup',
  template: ''
})
export class SwimerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ swimer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SwimerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.swimer = swimer;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/swimer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/swimer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
