import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestNage } from 'app/shared/model/test-nage.model';

type EntityResponseType = HttpResponse<ITestNage>;
type EntityArrayResponseType = HttpResponse<ITestNage[]>;

@Injectable({ providedIn: 'root' })
export class TestNageService {
  public resourceUrl = SERVER_API_URL + 'api/test-nages';

  constructor(protected http: HttpClient) {}

  create(testNage: ITestNage): Observable<EntityResponseType> {
    return this.http.post<ITestNage>(this.resourceUrl, testNage, { observe: 'response' });
  }

  update(testNage: ITestNage): Observable<EntityResponseType> {
    return this.http.put<ITestNage>(this.resourceUrl, testNage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestNage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestNage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
