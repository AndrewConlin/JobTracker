import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { JobStatus } from './models/job-status';

@Injectable({
  providedIn: 'root'
})
export class JobStatusService {
  private url = environment.baseUrl + 'api/jobStatuses';

  index(): Observable<JobStatus[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<JobStatus[]>(`${this.url}`)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
