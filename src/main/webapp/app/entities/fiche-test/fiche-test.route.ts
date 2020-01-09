import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FicheTest } from 'app/shared/model/fiche-test.model';
import { FicheTestService } from './fiche-test.service';
import { FicheTestComponent } from './fiche-test.component';
import { FicheTestDetailComponent } from './fiche-test-detail.component';
import { FicheTestUpdateComponent } from './fiche-test-update.component';
import { IFicheTest } from 'app/shared/model/fiche-test.model';

@Injectable({ providedIn: 'root' })
export class FicheTestResolve implements Resolve<IFicheTest> {
  constructor(private service: FicheTestService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFicheTest> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((ficheTest: HttpResponse<FicheTest>) => ficheTest.body));
    }
    return of(new FicheTest());
  }
}

export const ficheTestRoute: Routes = [
  {
    path: '',
    component: FicheTestComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.ficheTest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FicheTestDetailComponent,
    resolve: {
      ficheTest: FicheTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheTest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FicheTestUpdateComponent,
    resolve: {
      ficheTest: FicheTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheTest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FicheTestUpdateComponent,
    resolve: {
      ficheTest: FicheTestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheTest.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
