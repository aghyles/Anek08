import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { SwimerUpdateComponent } from 'app/entities/swimer/swimer-update.component';
import { SwimerService } from 'app/entities/swimer/swimer.service';
import { Swimer } from 'app/shared/model/swimer.model';

describe('Component Tests', () => {
  describe('Swimer Management Update Component', () => {
    let comp: SwimerUpdateComponent;
    let fixture: ComponentFixture<SwimerUpdateComponent>;
    let service: SwimerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [SwimerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SwimerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SwimerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SwimerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Swimer(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Swimer();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
