import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Groupe } from 'app/shared/model/groupe.model';
import { GroupeService } from './groupe.service';
import { GroupeComponent } from './groupe.component';
import { GroupeDetailComponent } from './groupe-detail.component';
import { GroupeUpdateComponent } from './groupe-update.component';
import { IGroupe } from 'app/shared/model/groupe.model';

@Injectable({ providedIn: 'root' })
export class GroupeResolve implements Resolve<IGroupe> {
  constructor(private service: GroupeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupe> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((groupe: HttpResponse<Groupe>) => groupe.body));
    }
    return of(new Groupe());
  }
}

export const groupeRoute: Routes = [
  {
    path: '',
    component: GroupeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.groupe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupeDetailComponent,
    resolve: {
      groupe: GroupeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.groupe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupeUpdateComponent,
    resolve: {
      groupe: GroupeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.groupe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupeUpdateComponent,
    resolve: {
      groupe: GroupeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.groupe.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
