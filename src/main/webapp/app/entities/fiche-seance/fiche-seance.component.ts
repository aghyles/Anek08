import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFicheSeance } from 'app/shared/model/fiche-seance.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FicheSeanceService } from './fiche-seance.service';
import { FicheSeanceDeleteDialogComponent } from './fiche-seance-delete-dialog.component';

@Component({
  selector: 'jhi-fiche-seance',
  templateUrl: './fiche-seance.component.html'
})
export class FicheSeanceComponent implements OnInit, OnDestroy {
  ficheSeances: IFicheSeance[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected ficheSeanceService: FicheSeanceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.ficheSeances = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.ficheSeanceService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IFicheSeance[]>) => this.paginateFicheSeances(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.ficheSeances = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInFicheSeances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFicheSeance) {
    return item.id;
  }

  registerChangeInFicheSeances() {
    this.eventSubscriber = this.eventManager.subscribe('ficheSeanceListModification', () => this.reset());
  }

  delete(ficheSeance: IFicheSeance) {
    const modalRef = this.modalService.open(FicheSeanceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ficheSeance = ficheSeance;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFicheSeances(data: IFicheSeance[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.ficheSeances.push(data[i]);
    }
  }
}
