import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { T04JhTestModule } from '../../../test.module';
import { TestEtudeDetailComponent } from 'app/entities/test-etude/test-etude-detail.component';
import { TestEtude } from 'app/shared/model/test-etude.model';

describe('Component Tests', () => {
  describe('TestEtude Management Detail Component', () => {
    let comp: TestEtudeDetailComponent;
    let fixture: ComponentFixture<TestEtudeDetailComponent>;
    const route = ({ data: of({ testEtude: new TestEtude(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [TestEtudeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TestEtudeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestEtudeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testEtude).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
