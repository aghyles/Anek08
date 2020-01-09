import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestEtude } from 'app/shared/model/test-etude.model';

type EntityResponseType = HttpResponse<ITestEtude>;
type EntityArrayResponseType = HttpResponse<ITestEtude[]>;

@Injectable({ providedIn: 'root' })
export class TestEtudeService {
  public resourceUrl = SERVER_API_URL + 'api/test-etudes';

  constructor(protected http: HttpClient) {}

  create(testEtude: ITestEtude): Observable<EntityResponseType> {
    return this.http.post<ITestEtude>(this.resourceUrl, testEtude, { observe: 'response' });
  }

  update(testEtude: ITestEtude): Observable<EntityResponseType> {
    return this.http.put<ITestEtude>(this.resourceUrl, testEtude, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestEtude>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestEtude[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
