import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';

type EntityResponseType = HttpResponse<IFicheSeance>;
type EntityArrayResponseType = HttpResponse<IFicheSeance[]>;

@Injectable({ providedIn: 'root' })
export class FicheSeanceService {
  public resourceUrl = SERVER_API_URL + 'api/fiche-seances';

  constructor(protected http: HttpClient) {}

  create(ficheSeance: IFicheSeance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheSeance);
    return this.http
      .post<IFicheSeance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ficheSeance: IFicheSeance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheSeance);
    return this.http
      .put<IFicheSeance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFicheSeance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFicheSeance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ficheSeance: IFicheSeance): IFicheSeance {
    const copy: IFicheSeance = Object.assign({}, ficheSeance, {
      date: ficheSeance.date != null && ficheSeance.date.isValid() ? ficheSeance.date.toJSON() : null
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
      res.body.forEach((ficheSeance: IFicheSeance) => {
        ficheSeance.date = ficheSeance.date != null ? moment(ficheSeance.date) : null;
      });
    }
    return res;
  }
}
