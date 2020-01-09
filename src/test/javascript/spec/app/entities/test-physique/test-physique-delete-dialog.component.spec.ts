import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { TestPhysiqueDeleteDialogComponent } from 'app/entities/test-physique/test-physique-delete-dialog.component';
import { TestPhysiqueService } from 'app/entities/test-physique/test-physique.service';

describe('Component Tests', () => {
  describe('TestPhysique Management Delete Component', () => {
    let comp: TestPhysiqueDeleteDialogComponent;
    let fixture: ComponentFixture<TestPhysiqueDeleteDialogComponent>;
    let service: TestPhysiqueService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestPhysiqueDeleteDialogComponent]
      })
        .overrideTemplate(TestPhysiqueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestPhysiqueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestPhysiqueService);
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
