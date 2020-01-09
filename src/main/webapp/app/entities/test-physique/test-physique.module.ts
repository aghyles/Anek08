import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { TestPhysiqueComponent } from './test-physique.component';
import { TestPhysiqueDetailComponent } from './test-physique-detail.component';
import { TestPhysiqueUpdateComponent } from './test-physique-update.component';
import { TestPhysiqueDeleteDialogComponent } from './test-physique-delete-dialog.component';
import { testPhysiqueRoute } from './test-physique.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(testPhysiqueRoute)],
  declarations: [TestPhysiqueComponent, TestPhysiqueDetailComponent, TestPhysiqueUpdateComponent, TestPhysiqueDeleteDialogComponent],
  entryComponents: [TestPhysiqueDeleteDialogComponent]
})
export class T04JhTestPhysiqueModule {}
