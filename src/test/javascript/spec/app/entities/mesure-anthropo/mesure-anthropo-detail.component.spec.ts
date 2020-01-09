import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { MesureAnthropoDetailComponent } from 'app/entities/mesure-anthropo/mesure-anthropo-detail.component';
import { MesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

describe('Component Tests', () => {
  describe('MesureAnthropo Management Detail Component', () => {
    let comp: MesureAnthropoDetailComponent;
    let fixture: ComponentFixture<MesureAnthropoDetailComponent>;
    const route = ({ data: of({ mesureAnthropo: new MesureAnthropo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [MesureAnthropoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MesureAnthropoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MesureAnthropoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mesureAnthropo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
