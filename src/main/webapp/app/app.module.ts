import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { T04JhSharedModule } from 'app/shared/shared.module';
import { T04JhCoreModule } from 'app/core/core.module';
import { T04JhAppRoutingModule } from './app-routing.module';
import { T04JhHomeModule } from './home/home.module';
import { T04JhEntityModule } from './entities/entity.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import 'hammerjs';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/sidenav/navbar/navbar.component';
import { SidenavComponent } from './layouts/sidenav/sidenav.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/sidenav/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { T04JhDashboardModule } from 'app/Pages/dashboard/dashboard.module';
import { MaterialModule } from 'app/material.module';
import { SpeedDialFabComponent } from 'app/layouts/speeddialfab/speed-dial-fab.component';
import { OverlayModule } from '@angular/cdk/overlay';
import { CookieService } from 'ngx-cookie-service';
import { CalendarModule } from 'app/calendar/calendar.module';
import { T04JhDashboard2Module } from 'app/Pages/dashboard2/dashboard.module';
import { T04JhEntityUiModule } from 'app/Pages/entityUi/entityUi.module';
//  import { FullCalendarModule } from '@fullcalendar/angular'; // for FullCalendar!

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    T04JhSharedModule,
    T04JhCoreModule,
    T04JhHomeModule,
    T04JhDashboardModule,
    T04JhDashboard2Module,
    CalendarModule,
    T04JhEntityUiModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    T04JhEntityModule,
    T04JhAppRoutingModule,
    MaterialModule,
    //  FullCalendarModule // for FullCalendar!
    OverlayModule
  ],
  declarations: [
    JhiMainComponent,
    SpeedDialFabComponent,
    NavbarComponent,
    SidenavComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent
  ],
  bootstrap: [JhiMainComponent],
  providers: [CookieService]
})
export class T04JhAppModule {}
