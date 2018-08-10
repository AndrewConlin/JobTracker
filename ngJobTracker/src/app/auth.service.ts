import { AuthInterceptor } from './interceptors/auth-interceptor';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from '../environments/environment';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.baseUrl;

  login(username, password) {
    const loginUser = {
        'username': username,
        'password': password
    };

    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'}),
      observe: 'response' as 'response'
    };

    return this.http
      .post(`${this.url}login`, loginUser, httpOptions)
      .pipe(
        tap((res: HttpResponse<any>) => {
          const authHeader = res.headers.get('authorization'); // get entire auth header
          const token = authHeader.split(' ')[1];

          localStorage.setItem('token', token);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  register(user) {
    // create request to register a new account
    return this.http.post(`${this.url}auth/register`, user)
    .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
  }

  checkLogin() {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  constructor(private http: HttpClient) { }

}
