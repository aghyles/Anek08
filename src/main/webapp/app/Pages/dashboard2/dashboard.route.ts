import { Route } from '@angular/router';

import { Dashboard2Component} from './dashboard.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const DASHBOARD_ROUTE2: Route = {
  path: 'dashboard2',
  component: Dashboard2Component,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  },
  canActivate: [UserRouteAccessService]
};
