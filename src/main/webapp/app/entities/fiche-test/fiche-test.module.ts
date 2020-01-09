import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { FicheTestComponent } from './fiche-test.component';
import { FicheTestDetailComponent } from './fiche-test-detail.component';
import { FicheTestUpdateComponent } from './fiche-test-update.component';
import { FicheTestDeleteDialogComponent } from './fiche-test-delete-dialog.component';
import { ficheTestRoute } from './fiche-test.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(ficheTestRoute)],
  declarations: [FicheTestComponent, FicheTestDetailComponent, FicheTestUpdateComponent, FicheTestDeleteDialogComponent],
  entryComponents: [FicheTestDeleteDialogComponent]
})
export class T04JhFicheTestModule {}
