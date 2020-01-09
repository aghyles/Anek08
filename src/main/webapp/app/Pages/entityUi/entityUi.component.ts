import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGroupe } from 'app/shared/model/groupe.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GroupeService } from 'app/entities/groupe/groupe.service';
import { EntityUiDeleteDialogComponent } from './entityUi-delete-dialog.component';
import { SwimerService } from 'app/entities/swimer/swimer.service';
import { ISwimer } from 'app/shared/model/swimer.model';

@Component({
  selector: 'jhi-entityui',
  templateUrl: './entityUi.component.html'
})
export class EntityUiComponent implements OnInit, OnDestroy {
  groupes: IGroupe[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  eventSubscriberS: Subscription;
  itemsPerPageS: number;
  linksS: any;
  pageS: any;
  predicateS: any;
  reverseS: any;

  swimers: ISwimer[];
  swim=0;

  constructor(
    protected groupeService: GroupeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,

  protected swimerService: SwimerService,

  ) {
    this.groupes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;

    this.swimers = [];
    this.itemsPerPageS = ITEMS_PER_PAGE;
    this.pageS = 0;
    this.linksS = {
      lastS: 0
    };
    this.predicateS = 'id';
    this.reverseS = true;
  }

  loadAll() {
    this.groupeService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IGroupe[]>) => this.paginateGroupes(res.body, res.headers));

  }

  loadAllS() {
    this.swimerService
      .query({
        pageS: this.pageS,
        sizeS: this.itemsPerPageS,
        sortS: this.sort(),

      })
      .subscribe((res: HttpResponse<ISwimer[]>) => this.paginateSwimers(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.groupes = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  loadPageS(page) {
    this.pageS = 0;
    this.swimers = [];
    this.pageS = page;
    this.loadAllS();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInGroupes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGroupe) {
    return item.id;
  }

  registerChangeInGroupes() {
    this.eventSubscriber = this.eventManager.subscribe('groupeListModification', () => this.reset());
  }

  delete(groupe: IGroupe) {
    const modalRef = this.modalService.open(EntityUiDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.groupe = groupe;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGroupes(data: IGroupe[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.groupes.push(data[i]);
    }
  }



  protected paginateSwimers(data: ISwimer[], headers: HttpHeaders) {
    this.linksS = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {

      try{
        if(data[i].groupe.id===this.swim.valueOf())
          this.swimers.push(data[i]);
     }catch(e){
        // eslint-disable-next-line no-console
        console.log("YO",e);
      }

    }
  }


  swimi(id: number) {
    this.swim=id;
  }
}
