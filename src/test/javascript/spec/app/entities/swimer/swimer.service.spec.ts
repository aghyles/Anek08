import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SwimerService } from 'app/entities/swimer/swimer.service';
import { ISwimer, Swimer } from 'app/shared/model/swimer.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';

describe('Service Tests', () => {
  describe('Swimer Service', () => {
    let injector: TestBed;
    let service: SwimerService;
    let httpMock: HttpTestingController;
    let elemDefault: ISwimer;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SwimerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Swimer(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Sexe.HOMME,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            bearthday: currentDate.format(DATE_TIME_FORMAT),
            firstSwim: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Swimer', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            bearthday: currentDate.format(DATE_TIME_FORMAT),
            firstSwim: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            bearthday: currentDate,
            firstSwim: currentDate
          },
          returnedFromService
        );
        service
          .create(new Swimer(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Swimer', () => {
        const returnedFromService = Object.assign(
          {
            licenceNum: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            sexe: 'BBBBBB',
            bearthday: currentDate.format(DATE_TIME_FORMAT),
            phoneNumber: 'BBBBBB',
            eMail: 'BBBBBB',
            address: 'BBBBBB',
            studyTime: 'BBBBBB',
            firstSwim: currentDate.format(DATE_TIME_FORMAT),
            groupeName: 'BBBBBB',
            document: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            bearthday: currentDate,
            firstSwim: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Swimer', () => {
        const returnedFromService = Object.assign(
          {
            licenceNum: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            sexe: 'BBBBBB',
            bearthday: currentDate.format(DATE_TIME_FORMAT),
            phoneNumber: 'BBBBBB',
            eMail: 'BBBBBB',
            address: 'BBBBBB',
            studyTime: 'BBBBBB',
            firstSwim: currentDate.format(DATE_TIME_FORMAT),
            groupeName: 'BBBBBB',
            document: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            bearthday: currentDate,
            firstSwim: currentDate
          },
          returnedFromService
        );
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

      it('should delete a Swimer', () => {
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
