import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MesureAnthropo } from 'app/shared/model/mesure-anthropo.model';
import { MesureAnthropoService } from './mesure-anthropo.service';
import { MesureAnthropoComponent } from './mesure-anthropo.component';
import { MesureAnthropoDetailComponent } from './mesure-anthropo-detail.component';
import { MesureAnthropoUpdateComponent } from './mesure-anthropo-update.component';
import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

@Injectable({ providedIn: 'root' })
export class MesureAnthropoResolve implements Resolve<IMesureAnthropo> {
  constructor(private service: MesureAnthropoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMesureAnthropo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((mesureAnthropo: HttpResponse<MesureAnthropo>) => mesureAnthropo.body));
    }
    return of(new MesureAnthropo());
  }
}

export const mesureAnthropoRoute: Routes = [
  {
    path: '',
    component: MesureAnthropoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.mesureAnthropo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MesureAnthropoDetailComponent,
    resolve: {
      mesureAnthropo: MesureAnthropoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.mesureAnthropo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MesureAnthropoUpdateComponent,
    resolve: {
      mesureAnthropo: MesureAnthropoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.mesureAnthropo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MesureAnthropoUpdateComponent,
    resolve: {
      mesureAnthropo: MesureAnthropoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.mesureAnthropo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
