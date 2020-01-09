import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestPhysiqueUpdateComponent } from 'app/entities/test-physique/test-physique-update.component';
import { TestPhysiqueService } from 'app/entities/test-physique/test-physique.service';
import { TestPhysique } from 'app/shared/model/test-physique.model';

describe('Component Tests', () => {
  describe('TestPhysique Management Update Component', () => {
    let comp: TestPhysiqueUpdateComponent;
    let fixture: ComponentFixture<TestPhysiqueUpdateComponent>;
    let service: TestPhysiqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestPhysiqueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TestPhysiqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TestPhysiqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TestPhysiqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TestPhysique(123);
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
        const entity = new TestPhysique();
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
