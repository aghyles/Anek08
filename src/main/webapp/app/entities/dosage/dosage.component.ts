import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDosage } from 'app/shared/model/dosage.model';
import { DosageService } from './dosage.service';
import { DosageDeleteDialogComponent } from './dosage-delete-dialog.component';

@Component({
  selector: 'jhi-dosage',
  templateUrl: './dosage.component.html'
})
export class DosageComponent implements OnInit, OnDestroy {
  dosages: IDosage[];
  eventSubscriber: Subscription;

  constructor(protected dosageService: DosageService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.dosageService.query().subscribe((res: HttpResponse<IDosage[]>) => {
      this.dosages = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDosages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDosage) {
    return item.id;
  }

  registerChangeInDosages() {
    this.eventSubscriber = this.eventManager.subscribe('dosageListModification', () => this.loadAll());
  }

  delete(dosage: IDosage) {
    const modalRef = this.modalService.open(DosageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dosage = dosage;
  }
}
