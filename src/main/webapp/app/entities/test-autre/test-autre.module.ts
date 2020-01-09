import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { TestAutreComponent } from './test-autre.component';
import { TestAutreDetailComponent } from './test-autre-detail.component';
import { TestAutreUpdateComponent } from './test-autre-update.component';
import { TestAutreDeleteDialogComponent } from './test-autre-delete-dialog.component';
import { testAutreRoute } from './test-autre.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(testAutreRoute)],
  declarations: [TestAutreComponent, TestAutreDetailComponent, TestAutreUpdateComponent, TestAutreDeleteDialogComponent],
  entryComponents: [TestAutreDeleteDialogComponent]
})
export class T04JhTestAutreModule {}
