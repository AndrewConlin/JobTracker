import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';
import { Board } from './models/board';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private url = environment.baseUrl + 'api/boards';

  index(): Observable<Board[]> {

    const httpOptions = {
      headers: new HttpHeaders({
      })
    };


    return this.http.get<Board[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  show(id): Observable<Board> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Board>(`${this.url}/${id}`, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  create(board: Board): Observable<Board> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<Board>(`${this.url}`, board, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  update(board: Board, id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.patch(`${this.url}/${id}`, board, httpOptions)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  destroy(id: number) {
    return this.http.delete(`${this.url}/${id}`)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
