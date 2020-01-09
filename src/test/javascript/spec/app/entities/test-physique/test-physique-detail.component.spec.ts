import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestPhysiqueDetailComponent } from 'app/entities/test-physique/test-physique-detail.component';
import { TestPhysique } from 'app/shared/model/test-physique.model';

describe('Component Tests', () => {
  describe('TestPhysique Management Detail Component', () => {
    let comp: TestPhysiqueDetailComponent;
    let fixture: ComponentFixture<TestPhysiqueDetailComponent>;
    const route = ({ data: of({ testPhysique: new TestPhysique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestPhysiqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TestPhysiqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestPhysiqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testPhysique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
