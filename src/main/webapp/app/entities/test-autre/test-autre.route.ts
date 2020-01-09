import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TestAutre } from 'app/shared/model/test-autre.model';
import { TestAutreService } from './test-autre.service';
import { TestAutreComponent } from './test-autre.component';
import { TestAutreDetailComponent } from './test-autre-detail.component';
import { TestAutreUpdateComponent } from './test-autre-update.component';
import { ITestAutre } from 'app/shared/model/test-autre.model';

@Injectable({ providedIn: 'root' })
export class TestAutreResolve implements Resolve<ITestAutre> {
  constructor(private service: TestAutreService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestAutre> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((testAutre: HttpResponse<TestAutre>) => testAutre.body));
    }
    return of(new TestAutre());
  }
}

export const testAutreRoute: Routes = [
  {
    path: '',
    component: TestAutreComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.testAutre.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TestAutreDetailComponent,
    resolve: {
      testAutre: TestAutreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testAutre.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TestAutreUpdateComponent,
    resolve: {
      testAutre: TestAutreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testAutre.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TestAutreUpdateComponent,
    resolve: {
      testAutre: TestAutreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testAutre.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
