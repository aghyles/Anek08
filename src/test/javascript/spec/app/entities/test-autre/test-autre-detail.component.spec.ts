import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestAutreDetailComponent } from 'app/entities/test-autre/test-autre-detail.component';
import { TestAutre } from 'app/shared/model/test-autre.model';

describe('Component Tests', () => {
  describe('TestAutre Management Detail Component', () => {
    let comp: TestAutreDetailComponent;
    let fixture: ComponentFixture<TestAutreDetailComponent>;
    const route = ({ data: of({ testAutre: new TestAutre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestAutreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TestAutreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestAutreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testAutre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
