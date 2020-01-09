import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Objectif } from 'app/shared/model/objectif.model';
import { ObjectifService } from './objectif.service';
import { ObjectifComponent } from './objectif.component';
import { ObjectifDetailComponent } from './objectif-detail.component';
import { ObjectifUpdateComponent } from './objectif-update.component';
import { IObjectif } from 'app/shared/model/objectif.model';

@Injectable({ providedIn: 'root' })
export class ObjectifResolve implements Resolve<IObjectif> {
  constructor(private service: ObjectifService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObjectif> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((objectif: HttpResponse<Objectif>) => objectif.body));
    }
    return of(new Objectif());
  }
}

export const objectifRoute: Routes = [
  {
    path: '',
    component: ObjectifComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.objectif.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ObjectifDetailComponent,
    resolve: {
      objectif: ObjectifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.objectif.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ObjectifUpdateComponent,
    resolve: {
      objectif: ObjectifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.objectif.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ObjectifUpdateComponent,
    resolve: {
      objectif: ObjectifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.objectif.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
