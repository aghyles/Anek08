import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { DosageUpdateComponent } from 'app/entities/dosage/dosage-update.component';
import { DosageService } from 'app/entities/dosage/dosage.service';
import { Dosage } from 'app/shared/model/dosage.model';

describe('Component Tests', () => {
  describe('Dosage Management Update Component', () => {
    let comp: DosageUpdateComponent;
    let fixture: ComponentFixture<DosageUpdateComponent>;
    let service: DosageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [DosageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DosageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DosageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DosageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Dosage(123);
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
        const entity = new Dosage();
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
