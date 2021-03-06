import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Theme } from 'app/shared/model/theme.model';
import { ThemeService } from './theme.service';
import { ThemeComponent } from './theme.component';
import { ThemeDetailComponent } from './theme-detail.component';
import { ThemeUpdateComponent } from './theme-update.component';
import { ITheme } from 'app/shared/model/theme.model';

@Injectable({ providedIn: 'root' })
export class ThemeResolve implements Resolve<ITheme> {
  constructor(private service: ThemeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITheme> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((theme: HttpResponse<Theme>) => theme.body));
    }
    return of(new Theme());
  }
}

export const themeRoute: Routes = [
  {
    path: '',
    component: ThemeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 't04JhApp.theme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThemeDetailComponent,
    resolve: {
      theme: ThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.theme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThemeUpdateComponent,
    resolve: {
      theme: ThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.theme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThemeUpdateComponent,
    resolve: {
      theme: ThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 't04JhApp.theme.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
