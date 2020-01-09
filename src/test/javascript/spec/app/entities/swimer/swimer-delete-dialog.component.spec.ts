import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { T04JhTestModule } from '../../../test.module';
import { SwimerDeleteDialogComponent } from 'app/entities/swimer/swimer-delete-dialog.component';
import { SwimerService } from 'app/entities/swimer/swimer.service';

describe('Component Tests', () => {
  describe('Swimer Management Delete Component', () => {
    let comp: SwimerDeleteDialogComponent;
    let fixture: ComponentFixture<SwimerDeleteDialogComponent>;
    let service: SwimerService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [SwimerDeleteDialogComponent]
      })
        .overrideTemplate(SwimerDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SwimerDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SwimerService);
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
