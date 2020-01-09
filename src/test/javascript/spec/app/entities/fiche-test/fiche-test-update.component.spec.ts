import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { FicheTestUpdateComponent } from 'app/entities/fiche-test/fiche-test-update.component';
import { FicheTestService } from 'app/entities/fiche-test/fiche-test.service';
import { FicheTest } from 'app/shared/model/fiche-test.model';

describe('Component Tests', () => {
  describe('FicheTest Management Update Component', () => {
    let comp: FicheTestUpdateComponent;
    let fixture: ComponentFixture<FicheTestUpdateComponent>;
    let service: FicheTestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheTestUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FicheTestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FicheTestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FicheTestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FicheTest(123);
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
        const entity = new FicheTest();
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
