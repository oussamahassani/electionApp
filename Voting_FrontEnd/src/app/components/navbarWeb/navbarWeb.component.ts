import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-navbarWeb',
  templateUrl: './navbarWeb.component.html',
  styleUrls: ['./navbarWeb.component.css']
})
export class NavbarWebComponent {
  isLogin = false;
  curentUser: any = null;
  constructor(private _login: LoginService,
    private router: Router) {
    let isConnected = localStorage.getItem('user')
    if (isConnected) {
      this.isLogin = true
      this.curentUser = isConnected
    }
  }

  public logout() {
    this._login.logout()
    this.router.navigate(['login'])
  }
  public getDashbord() {
    let currentUser = JSON.parse(this.curentUser)
    console.log(currentUser)
    if (currentUser.authorities[0].authority == "ADMIN") {
      this.router.navigate(['/admin/profile'])
    }
    else {
      this.router.navigate(['/voter/profile'])
    }
  }
}
