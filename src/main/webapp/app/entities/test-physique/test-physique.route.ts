import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TestPhysique } from 'app/shared/model/test-physique.model';
import { TestPhysiqueService } from './test-physique.service';
import { TestPhysiqueComponent } from './test-physique.component';
import { TestPhysiqueDetailComponent } from './test-physique-detail.component';
import { TestPhysiqueUpdateComponent } from './test-physique-update.component';
import { ITestPhysique } from 'app/shared/model/test-physique.model';

@Injectable({ providedIn: 'root' })
export class TestPhysiqueResolve implements Resolve<ITestPhysique> {
  constructor(private service: TestPhysiqueService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestPhysique> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((testPhysique: HttpResponse<TestPhysique>) => testPhysique.body));
    }
    return of(new TestPhysique());
  }
}

export const testPhysiqueRoute: Routes = [
  {
    path: '',
    component: TestPhysiqueComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.testPhysique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TestPhysiqueDetailComponent,
    resolve: {
      testPhysique: TestPhysiqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testPhysique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TestPhysiqueUpdateComponent,
    resolve: {
      testPhysique: TestPhysiqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testPhysique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TestPhysiqueUpdateComponent,
    resolve: {
      testPhysique: TestPhysiqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.testPhysique.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
