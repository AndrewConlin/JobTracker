import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  register(form: NgForm) {
    const user: User = new User(form.value.username, form.value.email, form.value.password);

    this.authService.register(user).subscribe(
      (data) => {
        this.authService.login(user.username, user.password).subscribe(
          (dataLogin) => this.router.navigateByUrl('boards'),
          (err) => console.log(err)
        );
      },
      (err) => console.log(err)
    );
  }

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

}
