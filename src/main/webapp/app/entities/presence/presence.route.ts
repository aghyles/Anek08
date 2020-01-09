import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Presence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';
import { PresenceComponent } from './presence.component';
import { PresenceDetailComponent } from './presence-detail.component';
import { PresenceUpdateComponent } from './presence-update.component';
import { IPresence } from 'app/shared/model/presence.model';

@Injectable({ providedIn: 'root' })
export class PresenceResolve implements Resolve<IPresence> {
  constructor(private service: PresenceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPresence> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((presence: HttpResponse<Presence>) => presence.body));
    }
    return of(new Presence());
  }
}

export const presenceRoute: Routes = [
  {
    path: '',
    component: PresenceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.presence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PresenceDetailComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.presence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PresenceUpdateComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.presence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PresenceUpdateComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.presence.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
