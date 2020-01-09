import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { FicheSeanceUpdateComponent } from 'app/entities/fiche-seance/fiche-seance-update.component';
import { FicheSeanceService } from 'app/entities/fiche-seance/fiche-seance.service';
import { FicheSeance } from 'app/shared/model/fiche-seance.model';

describe('Component Tests', () => {
  describe('FicheSeance Management Update Component', () => {
    let comp: FicheSeanceUpdateComponent;
    let fixture: ComponentFixture<FicheSeanceUpdateComponent>;
    let service: FicheSeanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheSeanceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FicheSeanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FicheSeanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FicheSeanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FicheSeance(123);
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
        const entity = new FicheSeance();
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
