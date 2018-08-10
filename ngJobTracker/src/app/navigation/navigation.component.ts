import { AuthService } from '../auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  logo = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh654JTvJ0dDrg5hO7RvsJAn6PgnIaXEmGjKhHWIPmlQ-J4WK5yg';

  isLoggedIn() {
    return this.authService.checkLogin();
  }

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

}
