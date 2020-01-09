import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { TestNageDeleteDialogComponent } from 'app/entities/test-nage/test-nage-delete-dialog.component';
import { TestNageService } from 'app/entities/test-nage/test-nage.service';

describe('Component Tests', () => {
  describe('TestNage Management Delete Component', () => {
    let comp: TestNageDeleteDialogComponent;
    let fixture: ComponentFixture<TestNageDeleteDialogComponent>;
    let service: TestNageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestNageDeleteDialogComponent]
      })
        .overrideTemplate(TestNageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestNageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestNageService);
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
