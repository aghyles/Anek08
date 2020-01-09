import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FicheSeance } from 'app/shared/model/fiche-seance.model';
import { FicheSeanceService } from './fiche-seance.service';
import { FicheSeanceComponent } from './fiche-seance.component';
import { FicheSeanceDetailComponent } from './fiche-seance-detail.component';
import { FicheSeanceUpdateComponent } from './fiche-seance-update.component';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';

@Injectable({ providedIn: 'root' })
export class FicheSeanceResolve implements Resolve<IFicheSeance> {
  constructor(private service: FicheSeanceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFicheSeance> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((ficheSeance: HttpResponse<FicheSeance>) => ficheSeance.body));
    }
    return of(new FicheSeance());
  }
}

export const ficheSeanceRoute: Routes = [
  {
    path: '',
    component: FicheSeanceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheSeance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FicheSeanceDetailComponent,
    resolve: {
      ficheSeance: FicheSeanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheSeance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FicheSeanceUpdateComponent,
    resolve: {
      ficheSeance: FicheSeanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheSeance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FicheSeanceUpdateComponent,
    resolve: {
      ficheSeance: FicheSeanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.ficheSeance.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
