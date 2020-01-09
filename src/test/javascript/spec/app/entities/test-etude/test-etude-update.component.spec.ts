import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestEtudeUpdateComponent } from 'app/entities/test-etude/test-etude-update.component';
import { TestEtudeService } from 'app/entities/test-etude/test-etude.service';
import { TestEtude } from 'app/shared/model/test-etude.model';

describe('Component Tests', () => {
  describe('TestEtude Management Update Component', () => {
    let comp: TestEtudeUpdateComponent;
    let fixture: ComponentFixture<TestEtudeUpdateComponent>;
    let service: TestEtudeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestEtudeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TestEtudeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestEtudeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestEtudeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TestEtude(123);
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
        const entity = new TestEtude();
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
