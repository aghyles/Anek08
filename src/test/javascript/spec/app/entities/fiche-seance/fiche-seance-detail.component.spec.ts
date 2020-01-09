import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { FicheSeanceDetailComponent } from 'app/entities/fiche-seance/fiche-seance-detail.component';
import { FicheSeance } from 'app/shared/model/fiche-seance.model';

describe('Component Tests', () => {
  describe('FicheSeance Management Detail Component', () => {
    let comp: FicheSeanceDetailComponent;
    let fixture: ComponentFixture<FicheSeanceDetailComponent>;
    const route = ({ data: of({ ficheSeance: new FicheSeance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [FicheSeanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FicheSeanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FicheSeanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ficheSeance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
