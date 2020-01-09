import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DocumentService } from 'app/entities/document/document.service';
import { IDocument, Document } from 'app/shared/model/document.model';
import { TypeDocument } from 'app/shared/model/enumerations/type-document.model';

describe('Service Tests', () => {
  describe('Document Service', () => {
    let injector: TestBed;
    let service: DocumentService;
    let httpMock: HttpTestingController;
    let elemDefault: IDocument;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DocumentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Document(0, 'AAAAAAA', currentDate, 'AAAAAAA', TypeDocument.PHOTOS);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDoc: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Document', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDoc: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateDoc: currentDate
          },
          returnedFromService
        );
        service
          .create(new Document(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Document', () => {
        const returnedFromService = Object.assign(
          {
            docTitle: 'BBBBBB',
            dateDoc: currentDate.format(DATE_TIME_FORMAT),
            linkDoc: 'BBBBBB',
            typeDoc: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDoc: currentDate
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

      it('should return a list of Document', () => {
        const returnedFromService = Object.assign(
          {
            docTitle: 'BBBBBB',
            dateDoc: currentDate.format(DATE_TIME_FORMAT),
            linkDoc: 'BBBBBB',
            typeDoc: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateDoc: currentDate
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

      it('should delete a Document', () => {
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
