import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GoogleKnowledgeGraphService {
  private url =
  `https://kgsearch.googleapis.com/v1/entities:search`;

  search(phrase: string): Observable<any> {
   let Params = new HttpParams();

   Params = Params.append('query', phrase);
   Params = Params.append('key', environment.googleAPIKey);
   Params = Params.append('limit', '5');
   Params = Params.append('indent', 'true');

    // Uncomment to enable API call
    return this.http.get(`${this.url}`, { params: Params })
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );

    // return null;
  }

  constructor(private http: HttpClient) { }
}
