import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { GroupeComponent } from './groupe.component';
import { GroupeDetailComponent } from './groupe-detail.component';
import { GroupeUpdateComponent } from './groupe-update.component';
import { GroupeDeleteDialogComponent } from './groupe-delete-dialog.component';
import { groupeRoute } from './groupe.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(groupeRoute)],
  declarations: [GroupeComponent, GroupeDetailComponent, GroupeUpdateComponent, GroupeDeleteDialogComponent],
  exports: [
    GroupeComponent
  ],
  entryComponents: [GroupeDeleteDialogComponent]
})
export class T04JhGroupeModule {}
