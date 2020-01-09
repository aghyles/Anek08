import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { FicheSeanceDeleteDialogComponent } from 'app/entities/fiche-seance/fiche-seance-delete-dialog.component';
import { FicheSeanceService } from 'app/entities/fiche-seance/fiche-seance.service';

describe('Component Tests', () => {
  describe('FicheSeance Management Delete Component', () => {
    let comp: FicheSeanceDeleteDialogComponent;
    let fixture: ComponentFixture<FicheSeanceDeleteDialogComponent>;
    let service: FicheSeanceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheSeanceDeleteDialogComponent]
      })
        .overrideTemplate(FicheSeanceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FicheSeanceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FicheSeanceService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
