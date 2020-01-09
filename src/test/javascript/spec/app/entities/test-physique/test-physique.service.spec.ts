import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { TestPhysiqueService } from 'app/entities/test-physique/test-physique.service';
import { ITestPhysique, TestPhysique } from 'app/shared/model/test-physique.model';

describe('Service Tests', () => {
  describe('TestPhysique Service', () => {
    let injector: TestBed;
    let service: TestPhysiqueService;
    let httpMock: HttpTestingController;
    let elemDefault: ITestPhysique;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(TestPhysiqueService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TestPhysique(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a TestPhysique', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new TestPhysique(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a TestPhysique', () => {
        const returnedFromService = Object.assign(
          {
            testphyTitle: 'BBBBBB',
            testPhy1: 'BBBBBB',
            testPhy2: 'BBBBBB',
            testPhy3: 'BBBBBB',
            testPhy4: 'BBBBBB',
            testPhy5: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of TestPhysique', () => {
        const returnedFromService = Object.assign(
          {
            testphyTitle: 'BBBBBB',
            testPhy1: 'BBBBBB',
            testPhy2: 'BBBBBB',
            testPhy3: 'BBBBBB',
            testPhy4: 'BBBBBB',
            testPhy5: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TestPhysique', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
