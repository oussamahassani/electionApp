import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';
import { SweetAlertService } from 'src/app/services/sweet-alert.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginData = {
    username: '',
    password: '',
  };
  loginForm: FormGroup;

  constructor(
    private login: LoginService,
    private router: Router,
    private fb: FormBuilder,
    private sweetAlert: SweetAlertService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  formSubmit() {

    if (this.loginData.username.trim() == '' || this.loginData.username == null) {
      this.sweetAlert.showToast('error', 'Please enter Username');
      return
    }
    if (this.loginData.password.trim() == '' || this.loginData.password == null) {
      this.sweetAlert.showToast('error', 'Please enter password');
      return
    }

    //request ot server to generate token
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        //success
        //sweat alert
        //  console.log(data)
        this.sweetAlert.showToast('success', 'Logged in successfully')

        //login
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user: any) => {
            // console.log(user)
            this.login.setUser(user);

            console.log(this.login.getUserRole())
            if (this.login.getUserRole() == 'ADMIN') {

              //redirect ..ADMIN: admin-dashboard
              this.router.navigate(['/admin/dashboerd'])
              this.login.status = true
              this.login.loginSubjectStatus.next(true);

            } else if (this.login.getUserRole() == "NORMAL") {




              //redirect ..NORMAL: normal-dashboard
              this.router.navigate(['/voter/dashboard'])
              this.login.status = true
              this.login.loginSubjectStatus.next(true);

            } else {
              this.login.logout();
            }
          },

          (error: any) => {
            console.log(error)
            this.sweetAlert.showToast('error', 'Something went wrong !!')
          }
        );

      },
      (error) => {

        //error
        // console.log(error.error.message)
        if (error.error.message == "Bad credentials") {
          this.sweetAlert.showToast('error', 'Invalid Credentails')
        } else {
          this.sweetAlert.showToast('error', error.error.message)
        }
        //clears the username & password
        //  this.formClear()
      }
    )
  }

  formClear() {
    this.loginData.username = '';
    this.loginData.password = '';
  }
  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
