import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { EntityUiComponent } from './entityUi.component';
import { EntityUiDetailComponent } from './entityUi-detail.component';
import { EntityUiUpdateComponent } from './entityUi-update.component';
import { EntityUiDeleteDialogComponent } from './entityUi-delete-dialog.component';
import { entityUiRoute } from './entityUi.route';
import { CalendarModule } from 'app/calendar/calendar.module';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(entityUiRoute), CalendarModule],
  declarations: [EntityUiComponent, EntityUiDetailComponent, EntityUiUpdateComponent, EntityUiDeleteDialogComponent],
  exports: [
    EntityUiComponent
  ],
  entryComponents: [EntityUiDeleteDialogComponent]
})
export class T04JhEntityUiModule {}
