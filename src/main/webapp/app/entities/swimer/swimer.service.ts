import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISwimer } from 'app/shared/model/swimer.model';

type EntityResponseType = HttpResponse<ISwimer>;
type EntityArrayResponseType = HttpResponse<ISwimer[]>;

@Injectable({ providedIn: 'root' })
export class SwimerService {
  public resourceUrl = SERVER_API_URL + 'api/swimers';

  constructor(protected http: HttpClient) {}

  create(swimer: ISwimer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(swimer);
    return this.http
      .post<ISwimer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(swimer: ISwimer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(swimer);
    return this.http
      .put<ISwimer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISwimer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }


  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISwimer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(swimer: ISwimer): ISwimer {
    const copy: ISwimer = Object.assign({}, swimer, {
      bearthday: swimer.bearthday != null && swimer.bearthday.isValid() ? swimer.bearthday.toJSON() : null,
      firstSwim: swimer.firstSwim != null && swimer.firstSwim.isValid() ? swimer.firstSwim.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.bearthday = res.body.bearthday != null ? moment(res.body.bearthday) : null;
      res.body.firstSwim = res.body.firstSwim != null ? moment(res.body.firstSwim) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((swimer: ISwimer) => {
        swimer.bearthday = swimer.bearthday != null ? moment(swimer.bearthday) : null;
        swimer.firstSwim = swimer.firstSwim != null ? moment(swimer.firstSwim) : null;
      });
    }
    return res;
  }
}
