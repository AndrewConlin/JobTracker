import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';
import { Company } from './models/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private url = environment.baseUrl + 'api/jobs';

  update(company: Company, jid: number): Observable<Company>  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.patch<Company>(`${this.url}/${jid}/companies/${company.id}`, company, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
