import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { TestNageComponent } from './test-nage.component';
import { TestNageDetailComponent } from './test-nage-detail.component';
import { TestNageUpdateComponent } from './test-nage-update.component';
import { TestNageDeleteDialogComponent } from './test-nage-delete-dialog.component';
import { testNageRoute } from './test-nage.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(testNageRoute)],
  declarations: [TestNageComponent, TestNageDetailComponent, TestNageUpdateComponent, TestNageDeleteDialogComponent],
  entryComponents: [TestNageDeleteDialogComponent]
})
export class T04JhTestNageModule {}
