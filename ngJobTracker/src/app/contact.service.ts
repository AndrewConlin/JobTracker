import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';
import { Contact } from './models/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private url = environment.baseUrl + 'api/jobs';

  index(jid: number): Observable<Contact[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Contact[]>(`${this.url}/${jid}/contacts`, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  create(contact: Contact, jid: number): Observable<Contact>  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<Contact>(`${this.url}/${jid}/contacts`, contact, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  update(contact: Contact, jid: number): Observable<Contact>  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.patch<Contact>(`${this.url}/${jid}/contacts/${contact.id}`, contact, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
