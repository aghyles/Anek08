import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestAutre } from 'app/shared/model/test-autre.model';

type EntityResponseType = HttpResponse<ITestAutre>;
type EntityArrayResponseType = HttpResponse<ITestAutre[]>;

@Injectable({ providedIn: 'root' })
export class TestAutreService {
  public resourceUrl = SERVER_API_URL + 'api/test-autres';

  constructor(protected http: HttpClient) {}

  create(testAutre: ITestAutre): Observable<EntityResponseType> {
    return this.http.post<ITestAutre>(this.resourceUrl, testAutre, { observe: 'response' });
  }

  update(testAutre: ITestAutre): Observable<EntityResponseType> {
    return this.http.put<ITestAutre>(this.resourceUrl, testAutre, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestAutre>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestAutre[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
