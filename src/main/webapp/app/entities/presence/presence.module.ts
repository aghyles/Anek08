import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { PresenceComponent } from './presence.component';
import { PresenceDetailComponent } from './presence-detail.component';
import { PresenceUpdateComponent } from './presence-update.component';
import { PresenceDeleteDialogComponent } from './presence-delete-dialog.component';
import { presenceRoute } from './presence.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(presenceRoute)],
  declarations: [PresenceComponent, PresenceDetailComponent, PresenceUpdateComponent, PresenceDeleteDialogComponent],
  entryComponents: [PresenceDeleteDialogComponent]
})
export class T04JhPresenceModule {}
