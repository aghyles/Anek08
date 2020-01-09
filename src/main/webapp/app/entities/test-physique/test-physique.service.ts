import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestPhysique } from 'app/shared/model/test-physique.model';

type EntityResponseType = HttpResponse<ITestPhysique>;
type EntityArrayResponseType = HttpResponse<ITestPhysique[]>;

@Injectable({ providedIn: 'root' })
export class TestPhysiqueService {
  public resourceUrl = SERVER_API_URL + 'api/test-physiques';

  constructor(protected http: HttpClient) {}

  create(testPhysique: ITestPhysique): Observable<EntityResponseType> {
    return this.http.post<ITestPhysique>(this.resourceUrl, testPhysique, { observe: 'response' });
  }

  update(testPhysique: ITestPhysique): Observable<EntityResponseType> {
    return this.http.put<ITestPhysique>(this.resourceUrl, testPhysique, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestPhysique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestPhysique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
