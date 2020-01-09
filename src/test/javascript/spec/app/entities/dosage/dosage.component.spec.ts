import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { T04JhTestModule } from '../../../test.module';
import { DosageComponent } from 'app/entities/dosage/dosage.component';
import { DosageService } from 'app/entities/dosage/dosage.service';
import { Dosage } from 'app/shared/model/dosage.model';

describe('Component Tests', () => {
  describe('Dosage Management Component', () => {
    let comp: DosageComponent;
    let fixture: ComponentFixture<DosageComponent>;
    let service: DosageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [T04JhTestModule],
        declarations: [DosageComponent],
        providers: []
      })
        .overrideTemplate(DosageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DosageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DosageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Dosage(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dosages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
