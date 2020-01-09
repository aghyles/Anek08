import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MesureAnthropoService } from './mesure-anthropo.service';
import { MesureAnthropoDeleteDialogComponent } from './mesure-anthropo-delete-dialog.component';

@Component({
  selector: 'jhi-mesure-anthropo',
  templateUrl: './mesure-anthropo.component.html'
})
export class MesureAnthropoComponent implements OnInit, OnDestroy {
  mesureAnthropos: IMesureAnthropo[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected mesureAnthropoService: MesureAnthropoService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.mesureAnthropoService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMesureAnthropo[]>) => this.paginateMesureAnthropos(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/mesure-anthropo'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/mesure-anthropo',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInMesureAnthropos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMesureAnthropo) {
    return item.id;
  }

  registerChangeInMesureAnthropos() {
    this.eventSubscriber = this.eventManager.subscribe('mesureAnthropoListModification', () => this.loadAll());
  }

  delete(mesureAnthropo: IMesureAnthropo) {
    const modalRef = this.modalService.open(MesureAnthropoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mesureAnthropo = mesureAnthropo;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMesureAnthropos(data: IMesureAnthropo[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.mesureAnthropos = data;
  }
}
