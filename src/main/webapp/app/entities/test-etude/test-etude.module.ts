import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { TestEtudeComponent } from './test-etude.component';
import { TestEtudeDetailComponent } from './test-etude-detail.component';
import { TestEtudeUpdateComponent } from './test-etude-update.component';
import { TestEtudeDeleteDialogComponent } from './test-etude-delete-dialog.component';
import { testEtudeRoute } from './test-etude.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(testEtudeRoute)],
  declarations: [TestEtudeComponent, TestEtudeDetailComponent, TestEtudeUpdateComponent, TestEtudeDeleteDialogComponent],
  entryComponents: [TestEtudeDeleteDialogComponent]
})
export class T04JhTestEtudeModule {}
