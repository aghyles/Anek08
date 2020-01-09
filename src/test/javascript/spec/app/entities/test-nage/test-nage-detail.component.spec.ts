import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestNageDetailComponent } from 'app/entities/test-nage/test-nage-detail.component';
import { TestNage } from 'app/shared/model/test-nage.model';

describe('Component Tests', () => {
  describe('TestNage Management Detail Component', () => {
    let comp: TestNageDetailComponent;
    let fixture: ComponentFixture<TestNageDetailComponent>;
    const route = ({ data: of({ testNage: new TestNage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestNageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TestNageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestNageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testNage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
