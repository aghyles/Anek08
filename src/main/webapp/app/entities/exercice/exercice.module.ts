import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { T04JhSharedModule } from 'app/shared/shared.module';
import { ExerciceComponent } from './exercice.component';
import { ExerciceDetailComponent } from './exercice-detail.component';
import { ExerciceUpdateComponent } from './exercice-update.component';
import { ExerciceDeleteDialogComponent } from './exercice-delete-dialog.component';
import { exerciceRoute } from './exercice.route';

@NgModule({
  imports: [T04JhSharedModule, RouterModule.forChild(exerciceRoute)],
  declarations: [ExerciceComponent, ExerciceDetailComponent, ExerciceUpdateComponent, ExerciceDeleteDialogComponent],
  entryComponents: [ExerciceDeleteDialogComponent]
})
export class T04JhExerciceModule {}
