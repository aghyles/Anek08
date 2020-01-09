import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupe } from 'app/shared/model/groupe.model';

type EntityResponseType = HttpResponse<IGroupe>;
type EntityArrayResponseType = HttpResponse<IGroupe[]>;

@Injectable({ providedIn: 'root' })
export class GroupeService {
  public resourceUrl = SERVER_API_URL + 'api/groupes';

  constructor(protected http: HttpClient) {}

  create(groupe: IGroupe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupe);
    return this.http
      .post<IGroupe>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(groupe: IGroupe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupe);
    return this.http
      .put<IGroupe>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGroupe>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGroupe[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(groupe: IGroupe): IGroupe {
    const copy: IGroupe = Object.assign({}, groupe, {
      startDate: groupe.startDate != null && groupe.startDate.isValid() ? groupe.startDate.toJSON() : null,
      endDate: groupe.endDate != null && groupe.endDate.isValid() ? groupe.endDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((groupe: IGroupe) => {
        groupe.startDate = groupe.startDate != null ? moment(groupe.startDate) : null;
        groupe.endDate = groupe.endDate != null ? moment(groupe.endDate) : null;
      });
    }
    return res;
  }
}
