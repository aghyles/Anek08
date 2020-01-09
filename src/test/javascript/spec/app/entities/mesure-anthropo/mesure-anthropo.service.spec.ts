import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MesureAnthropoService } from 'app/entities/mesure-anthropo/mesure-anthropo.service';
import { IMesureAnthropo, MesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

describe('Service Tests', () => {
  describe('MesureAnthropo Service', () => {
    let injector: TestBed;
    let service: MesureAnthropoService;
    let httpMock: HttpTestingController;
    let elemDefault: IMesureAnthropo;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MesureAnthropoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MesureAnthropo(0, currentDate, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a MesureAnthropo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );
        service
          .create(new MesureAnthropo(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MesureAnthropo', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT),
            poids: 1,
            stature: 1,
            tailleAssis: 1,
            longTronc: 1,
            longMembreInferieurs: 1,
            longMembreSuperieur: 1,
            anvergureBras: 1,
            longBras: 1,
            longJambes: 1,
            longPieds: 1,
            hauteurPied: 1,
            longMain: 1,
            diamMain: 1,
            diamBiacromial: 1,
            diamBicretal: 1,
            diamThorax: 1,
            circThorax: 1,
            circThoraxInspirant: 1,
            circThoraxExpirant: 1,
            circBrasContracte: 1,
            circBrasDecontract: 1,
            circCuisse: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate
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

      it('should return a list of MesureAnthropo', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT),
            poids: 1,
            stature: 1,
            tailleAssis: 1,
            longTronc: 1,
            longMembreInferieurs: 1,
            longMembreSuperieur: 1,
            anvergureBras: 1,
            longBras: 1,
            longJambes: 1,
            longPieds: 1,
            hauteurPied: 1,
            longMain: 1,
            diamMain: 1,
            diamBiacromial: 1,
            diamBicretal: 1,
            diamThorax: 1,
            circThorax: 1,
            circThoraxInspirant: 1,
            circThoraxExpirant: 1,
            circBrasContracte: 1,
            circBrasDecontract: 1,
            circCuisse: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate
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

      it('should delete a MesureAnthropo', () => {
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
