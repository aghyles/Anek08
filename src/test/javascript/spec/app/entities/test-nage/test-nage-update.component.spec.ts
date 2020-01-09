import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestNageUpdateComponent } from 'app/entities/test-nage/test-nage-update.component';
import { TestNageService } from 'app/entities/test-nage/test-nage.service';
import { TestNage } from 'app/shared/model/test-nage.model';

describe('Component Tests', () => {
  describe('TestNage Management Update Component', () => {
    let comp: TestNageUpdateComponent;
    let fixture: ComponentFixture<TestNageUpdateComponent>;
    let service: TestNageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestNageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TestNageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestNageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestNageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TestNage(123);
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
        const entity = new TestNage();
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
