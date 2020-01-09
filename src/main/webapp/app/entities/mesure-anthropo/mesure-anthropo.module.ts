import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { MesureAnthropoComponent } from './mesure-anthropo.component';
import { MesureAnthropoDetailComponent } from './mesure-anthropo-detail.component';
import { MesureAnthropoUpdateComponent } from './mesure-anthropo-update.component';
import { MesureAnthropoDeleteDialogComponent } from './mesure-anthropo-delete-dialog.component';
import { mesureAnthropoRoute } from './mesure-anthropo.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(mesureAnthropoRoute)],
  declarations: [
    MesureAnthropoComponent,
    MesureAnthropoDetailComponent,
    MesureAnthropoUpdateComponent,
    MesureAnthropoDeleteDialogComponent
  ],
  entryComponents: [MesureAnthropoDeleteDialogComponent]
})
export class T04JhMesureAnthropoModule {}
