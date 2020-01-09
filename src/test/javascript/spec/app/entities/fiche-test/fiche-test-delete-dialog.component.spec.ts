import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { FicheTestDeleteDialogComponent } from 'app/entities/fiche-test/fiche-test-delete-dialog.component';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';

describe('Component Tests', () => {
  describe('FicheTest Management Delete Component', () => {
    let comp: FicheTestDeleteDialogComponent;
    let fixture: ComponentFixture<FicheTestDeleteDialogComponent>;
    let service: FicheTestService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheTestDeleteDialogComponent]
      })
        .overrideTemplate(FicheTestDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FicheTestDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FicheTestService);
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
