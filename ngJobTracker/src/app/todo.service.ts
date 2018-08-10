import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Todo } from './models/todo';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private url = environment.baseUrl + 'api';

  indexUser(): Observable<Todo[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Todo[]>(`${this.url}/todos`, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  indexJob(jid): Observable<Todo[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Todo[]>(`${this.url}/jobs/${jid}/todos`, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  show(id): Observable<Todo> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Todo>(`${this.url}/todos/${id}`, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
     })
   );
  }

  create(todo: Todo) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post(`${this.url}/todos`, todo, httpOptions)
    .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  update(todo: Todo) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    if (todo.completed) {
      todo.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
    } else {
      todo.completeDate = '';
    }

    return this.http.patch(`${this.url}/todos/${todo.id}`, todo, httpOptions)
    .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  destroy(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.delete(`${this.url}/todos/${id}`, httpOptions)
    .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient,
              private datePipe: DatePipe,
              private router: Router) { }
}
