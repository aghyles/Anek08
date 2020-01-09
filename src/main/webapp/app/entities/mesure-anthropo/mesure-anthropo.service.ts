import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';

type EntityResponseType = HttpResponse<IMesureAnthropo>;
type EntityArrayResponseType = HttpResponse<IMesureAnthropo[]>;

@Injectable({ providedIn: 'root' })
export class MesureAnthropoService {
  public resourceUrl = SERVER_API_URL + 'api/mesure-anthropos';

  constructor(protected http: HttpClient) {}

  create(mesureAnthropo: IMesureAnthropo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mesureAnthropo);
    return this.http
      .post<IMesureAnthropo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mesureAnthropo: IMesureAnthropo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mesureAnthropo);
    return this.http
      .put<IMesureAnthropo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMesureAnthropo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMesureAnthropo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(mesureAnthropo: IMesureAnthropo): IMesureAnthropo {
    const copy: IMesureAnthropo = Object.assign({}, mesureAnthropo, {
      date: mesureAnthropo.date != null && mesureAnthropo.date.isValid() ? mesureAnthropo.date.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date != null ? moment(res.body.date) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((mesureAnthropo: IMesureAnthropo) => {
        mesureAnthropo.date = mesureAnthropo.date != null ? moment(mesureAnthropo.date) : null;
      });
    }
    return res;
  }
}
