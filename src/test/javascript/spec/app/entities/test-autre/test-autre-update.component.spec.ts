import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestAutreUpdateComponent } from 'app/entities/test-autre/test-autre-update.component';
import { TestAutreService } from 'app/entities/test-autre/test-autre.service';
import { TestAutre } from 'app/shared/model/test-autre.model';

describe('Component Tests', () => {
  describe('TestAutre Management Update Component', () => {
    let comp: TestAutreUpdateComponent;
    let fixture: ComponentFixture<TestAutreUpdateComponent>;
    let service: TestAutreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestAutreUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TestAutreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestAutreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestAutreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TestAutre(123);
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
        const entity = new TestAutre();
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
