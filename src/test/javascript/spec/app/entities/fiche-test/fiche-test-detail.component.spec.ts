import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { FicheTestDetailComponent } from 'app/entities/fiche-test/fiche-test-detail.component';
import { FicheTest } from 'app/shared/model/fiche-test.model';

describe('Component Tests', () => {
  describe('FicheTest Management Detail Component', () => {
    let comp: FicheTestDetailComponent;
    let fixture: ComponentFixture<FicheTestDetailComponent>;
    const route = ({ data: of({ ficheTest: new FicheTest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheTestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FicheTestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FicheTestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ficheTest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
