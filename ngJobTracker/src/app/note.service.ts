import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { Note } from './models/note';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private url = environment.baseUrl + 'api/jobs';

  index(jid): Observable<Note[]> {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };

    return this.http.get<Note[]>(`${this.url}/${jid}/notes`)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  create(note: Note, jid: number): Observable<Note> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<Note>(`${this.url}/${jid}/notes`, note)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  update(note: Note, jid: number): Observable<Note> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.patch<Note>(`${this.url}/${jid}/notes/${note.id}`, note, httpOptions)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  constructor(private http: HttpClient) { }
}
