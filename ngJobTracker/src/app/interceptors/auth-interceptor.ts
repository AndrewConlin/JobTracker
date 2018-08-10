import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from '../auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!this.authService.getToken()) {
      return next.handle(req);
    }

    const authReq = req.clone({
      setHeaders: {
        'Authorization': `Bearer ${this.authService.getToken()}`
      },
    });
    return next.handle(authReq);
  }

  constructor(private authService: AuthService) { }

}
