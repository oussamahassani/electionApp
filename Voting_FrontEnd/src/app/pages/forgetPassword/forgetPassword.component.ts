import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';
import { SweetAlertService } from 'src/app/services/sweet-alert.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-forgetPassword',
  templateUrl: './forgetPassword.component.html',
  styleUrls: ['./forgetPassword.component.css']
})
export class ForgetPasswordComponent {

  loginData = {
    cin: '',
  };
  loginForm: FormGroup;

  constructor(
    private login: LoginService,
    private router: Router,
    private fb: FormBuilder,
    private sweetAlert: SweetAlertService) {
    this.loginForm = this.fb.group({
      cin: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  formSubmit() {

    if (this.loginData.cin.trim() == '' || this.loginData.cin == null) {
      this.sweetAlert.showToast('error', 'Please enter cin');
      return
    }

    //request ot server to generate token
    this.login.forgetpassword(this.loginData.cin).subscribe(
      (data: any) => {
        //success
        //sweat alert
        //  console.log(data)
        this.sweetAlert.showToast('success', 'Email Send in successfully')



        // console.log(user)



        //redirect ..ADMIN: admin-dashboard
        this.router.navigate(['/admin/dashboerd'])

      },




      (error: any) => {

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
    this.loginData.cin = '';
  }
  get cin() {
    return this.loginForm.get('cin');
  }


}
