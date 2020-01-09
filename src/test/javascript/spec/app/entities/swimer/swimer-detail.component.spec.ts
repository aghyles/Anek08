import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { SwimerDetailComponent } from 'app/entities/swimer/swimer-detail.component';
import { Swimer } from 'app/shared/model/swimer.model';

describe('Component Tests', () => {
  describe('Swimer Management Detail Component', () => {
    let comp: SwimerDetailComponent;
    let fixture: ComponentFixture<SwimerDetailComponent>;
    const route = ({ data: of({ swimer: new Swimer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [SwimerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SwimerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SwimerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.swimer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
