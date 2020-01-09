import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TestEtude } from 'app/shared/model/test-etude.model';
import { TestEtudeService } from './test-etude.service';
import { TestEtudeComponent } from './test-etude.component';
import { TestEtudeDetailComponent } from './test-etude-detail.component';
import { TestEtudeUpdateComponent } from './test-etude-update.component';
import { ITestEtude } from 'app/shared/model/test-etude.model';

@Injectable({ providedIn: 'root' })
export class TestEtudeResolve implements Resolve<ITestEtude> {
  constructor(private service: TestEtudeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestEtude> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((testEtude: HttpResponse<TestEtude>) => testEtude.body));
    }
    return of(new TestEtude());
  }
}

export const testEtudeRoute: Routes = [
  {
    path: '',
    component: TestEtudeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.testEtude.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TestEtudeDetailComponent,
    resolve: {
      testEtude: TestEtudeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testEtude.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TestEtudeUpdateComponent,
    resolve: {
      testEtude: TestEtudeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testEtude.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TestEtudeUpdateComponent,
    resolve: {
      testEtude: TestEtudeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testEtude.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
