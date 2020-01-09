import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDosage } from 'app/shared/model/dosage.model';

type EntityResponseType = HttpResponse<IDosage>;
type EntityArrayResponseType = HttpResponse<IDosage[]>;

@Injectable({ providedIn: 'root' })
export class DosageService {
  public resourceUrl = SERVER_API_URL + 'api/dosages';

  constructor(protected http: HttpClient) {}

  create(dosage: IDosage): Observable<EntityResponseType> {
    return this.http.post<IDosage>(this.resourceUrl, dosage, { observe: 'response' });
  }

  update(dosage: IDosage): Observable<EntityResponseType> {
    return this.http.put<IDosage>(this.resourceUrl, dosage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDosage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDosage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
