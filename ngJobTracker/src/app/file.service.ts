import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from '../environments/environment';
import { catchError, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class FileService {
  private url = environment.baseUrl + 'aws/s3';

  getAllFiles(): Observable<any> {
    return this.http.get(`${this.url}/files`)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  getAllFilesForJob(jid: number): Observable<any> {
    return this.http.get(`${this.url}/job/${jid}/files`)
      .pipe(
        catchError((err: any) => {
         console.log(err);
         return throwError('KABOOM');
       })
     );
  }

  upload(file: File, info: any): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('data', JSON.stringify(info));

    return this.http.post(`${this.url}/upload/file`, formData)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
     })
   );
  }

  constructor(private http: HttpClient) { }
}
