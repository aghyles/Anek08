import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Dosage } from 'app/shared/model/dosage.model';
import { DosageService } from './dosage.service';
import { DosageComponent } from './dosage.component';
import { DosageDetailComponent } from './dosage-detail.component';
import { DosageUpdateComponent } from './dosage-update.component';
import { IDosage } from 'app/shared/model/dosage.model';

@Injectable({ providedIn: 'root' })
export class DosageResolve implements Resolve<IDosage> {
  constructor(private service: DosageService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDosage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dosage: HttpResponse<Dosage>) => dosage.body));
    }
    return of(new Dosage());
  }
}

export const dosageRoute: Routes = [
  {
    path: '',
    component: DosageComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.dosage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DosageDetailComponent,
    resolve: {
      dosage: DosageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.dosage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DosageUpdateComponent,
    resolve: {
      dosage: DosageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.dosage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DosageUpdateComponent,
    resolve: {
      dosage: DosageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.dosage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
