import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import {DASHBOARD_ROUTE2} from './dashboard.route';
import { Dashboard2Component} from './dashboard.component';
import {CalendarModule} from "app/calendar/calendar.module";
import { T04JhGroupeModule } from 'app/entities/groupe/groupe.module';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forRoot([DASHBOARD_ROUTE2], { useHash: true }), CalendarModule, T04JhGroupeModule],
  declarations: [Dashboard2Component],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class T04JhDashboard2Module {}
