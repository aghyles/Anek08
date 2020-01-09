import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { TestAutreDeleteDialogComponent } from 'app/entities/test-autre/test-autre-delete-dialog.component';
import { TestAutreService } from 'app/entities/test-autre/test-autre.service';

describe('Component Tests', () => {
  describe('TestAutre Management Delete Component', () => {
    let comp: TestAutreDeleteDialogComponent;
    let fixture: ComponentFixture<TestAutreDeleteDialogComponent>;
    let service: TestAutreService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestAutreDeleteDialogComponent]
      })
        .overrideTemplate(TestAutreDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestAutreDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestAutreService);
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
