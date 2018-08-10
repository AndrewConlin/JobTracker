import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { Job } from './models/job';

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private url = environment.baseUrl + 'api/boards';

  index(bid): Observable<Job[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Job[]>(`${this.url}/${bid}/jobs`)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  create(job: Job, bid: number): Observable<Job> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<Job>(`${this.url}/${bid}/jobs`, job, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  update(job: Job, jid: number, bid: number): Observable<Job> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.patch<Job>(`${this.url}/${bid}/jobs/${jid}`, job, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  destroy(bid: number, jid: number) {
    return this.http.delete(`${this.url}/${bid}/jobs/${jid}`)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
