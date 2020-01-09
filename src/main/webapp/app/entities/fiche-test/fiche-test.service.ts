import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFicheTest } from 'app/shared/model/fiche-test.model';

type EntityResponseType = HttpResponse<IFicheTest>;
type EntityArrayResponseType = HttpResponse<IFicheTest[]>;

@Injectable({ providedIn: 'root' })
export class FicheTestService {
  public resourceUrl = SERVER_API_URL + 'api/fiche-tests';

  constructor(protected http: HttpClient) {}

  create(ficheTest: IFicheTest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheTest);
    return this.http
      .post<IFicheTest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ficheTest: IFicheTest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheTest);
    return this.http
      .put<IFicheTest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFicheTest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFicheTest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ficheTest: IFicheTest): IFicheTest {
    const copy: IFicheTest = Object.assign({}, ficheTest, {
      datetest: ficheTest.datetest != null && ficheTest.datetest.isValid() ? ficheTest.datetest.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datetest = res.body.datetest != null ? moment(res.body.datetest) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ficheTest: IFicheTest) => {
        ficheTest.datetest = ficheTest.datetest != null ? moment(ficheTest.datetest) : null;
      });
    }
    return res;
  }
}
