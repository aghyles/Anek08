import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TestNage } from 'app/shared/model/test-nage.model';
import { TestNageService } from './test-nage.service';
import { TestNageComponent } from './test-nage.component';
import { TestNageDetailComponent } from './test-nage-detail.component';
import { TestNageUpdateComponent } from './test-nage-update.component';
import { ITestNage } from 'app/shared/model/test-nage.model';

@Injectable({ providedIn: 'root' })
export class TestNageResolve implements Resolve<ITestNage> {
  constructor(private service: TestNageService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestNage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((testNage: HttpResponse<TestNage>) => testNage.body));
    }
    return of(new TestNage());
  }
}

export const testNageRoute: Routes = [
  {
    path: '',
    component: TestNageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.testNage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TestNageDetailComponent,
    resolve: {
      testNage: TestNageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testNage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TestNageUpdateComponent,
    resolve: {
      testNage: TestNageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testNage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TestNageUpdateComponent,
    resolve: {
      testNage: TestNageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testNage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
