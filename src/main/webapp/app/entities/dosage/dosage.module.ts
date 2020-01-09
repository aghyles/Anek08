import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { DosageComponent } from './dosage.component';
import { DosageDetailComponent } from './dosage-detail.component';
import { DosageUpdateComponent } from './dosage-update.component';
import { DosageDeleteDialogComponent } from './dosage-delete-dialog.component';
import { dosageRoute } from './dosage.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(dosageRoute)],
  declarations: [DosageComponent, DosageDetailComponent, DosageUpdateComponent, DosageDeleteDialogComponent],
  entryComponents: [DosageDeleteDialogComponent]
})
export class T04JhDosageModule {}
