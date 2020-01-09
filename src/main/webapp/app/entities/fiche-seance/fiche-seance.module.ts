import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { FicheSeanceComponent } from './fiche-seance.component';
import { FicheSeanceDetailComponent } from './fiche-seance-detail.component';
import { FicheSeanceUpdateComponent } from './fiche-seance-update.component';
import { FicheSeanceDeleteDialogComponent } from './fiche-seance-delete-dialog.component';
import { ficheSeanceRoute } from './fiche-seance.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(ficheSeanceRoute)],
  declarations: [FicheSeanceComponent, FicheSeanceDetailComponent, FicheSeanceUpdateComponent, FicheSeanceDeleteDialogComponent],
  entryComponents: [FicheSeanceDeleteDialogComponent]
})
export class T04JhFicheSeanceModule {}
