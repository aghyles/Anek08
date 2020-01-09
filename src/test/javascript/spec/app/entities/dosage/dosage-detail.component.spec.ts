import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { DosageDetailComponent } from 'app/entities/dosage/dosage-detail.component';
import { Dosage } from 'app/shared/model/dosage.model';

describe('Component Tests', () => {
  describe('Dosage Management Detail Component', () => {
    let comp: DosageDetailComponent;
    let fixture: ComponentFixture<DosageDetailComponent>;
    const route = ({ data: of({ dosage: new Dosage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [DosageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DosageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DosageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dosage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
