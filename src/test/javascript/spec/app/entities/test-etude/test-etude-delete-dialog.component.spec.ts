import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { TestEtudeDeleteDialogComponent } from 'app/entities/test-etude/test-etude-delete-dialog.component';
import { TestEtudeService } from 'app/entities/test-etude/test-etude.service';

describe('Component Tests', () => {
  describe('TestEtude Management Delete Component', () => {
    let comp: TestEtudeDeleteDialogComponent;
    let fixture: ComponentFixture<TestEtudeDeleteDialogComponent>;
    let service: TestEtudeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestEtudeDeleteDialogComponent]
      })
        .overrideTemplate(TestEtudeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestEtudeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestEtudeService);
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
