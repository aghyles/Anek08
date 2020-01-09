import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { SwimerComponent } from './swimer.component';
import { SwimerDetailComponent } from './swimer-detail.component';
import { SwimerUpdateComponent } from './swimer-update.component';
import { SwimerDeletePopupComponent, SwimerDeleteDialogComponent } from './swimer-delete-dialog.component';
import { swimerRoute, swimerPopupRoute } from './swimer.route';

const ENTITY_STATES = [...swimerRoute, ...swimerPopupRoute];

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [SwimerComponent, SwimerDetailComponent, SwimerUpdateComponent, SwimerDeleteDialogComponent, SwimerDeletePopupComponent],
  entryComponents: [SwimerDeleteDialogComponent]
})
export class T04JhSwimerModule {}
