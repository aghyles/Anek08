import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { MesureAnthropoUpdateComponent } from 'app/entities/mesure-anthropo/mesure-anthropo-update.component';
import { MesureAnthropoService } from 'app/entities/mesure-anthropo/mesure-anthropo.service';
import { MesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

describe('Component Tests', () => {
  describe('MesureAnthropo Management Update Component', () => {
    let comp: MesureAnthropoUpdateComponent;
    let fixture: ComponentFixture<MesureAnthropoUpdateComponent>;
    let service: MesureAnthropoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [MesureAnthropoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MesureAnthropoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MesureAnthropoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MesureAnthropoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MesureAnthropo(123);
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
        const entity = new MesureAnthropo();
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
