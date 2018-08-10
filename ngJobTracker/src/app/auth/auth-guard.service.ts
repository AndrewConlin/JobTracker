import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  canActivate(): boolean {
    if (!this.authService.checkLogin()) {
      this.router.navigate(['home']);
      return false;
    }
    return true;
  }

  constructor(private router: Router, private authService: AuthService) { }
}
