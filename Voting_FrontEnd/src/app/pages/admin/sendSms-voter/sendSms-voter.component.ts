import { Component } from '@angular/core';
import { CandidateService } from 'src/app/services/candidate/candidate.service';
import { SweetAlertService } from 'src/app/services/sweet-alert.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-sendSms-voter',
  templateUrl: './sendSms-voter.component.html',
  styleUrls: ['./sendSms-voter.component.css']
})
export class sendSmsvoterComponent {

  registrationData = {
    phone: '',
    mesg: '',

  };
  loginForm: FormGroup;

  confirmPassword: any
  photo: any


  constructor(private smsService: CandidateService,
    private router: Router,
    private fb: FormBuilder,
    private sweetAlert: SweetAlertService) {
    this.photo = null;
    this.loginForm = this.fb.group({
      phone: ['', [Validators.required]],
      mesg: ['', [Validators.required, Validators.minLength(3)]],

    });
  }
  get phone() {
    return this.loginForm.get('firstName');
  }

  get mesg() {
    return this.loginForm.get('lastName');
  }

  formSubmit() {
   
    if (this.registrationData.phone.trim() == '' || this.registrationData.phone == null ||
      this.registrationData.mesg.trim() == '' || this.registrationData.mesg == null) {

      this.sweetAlert.showToast('info', 'Please fill all the details to procced further.');
      return
    }






    this.smsService.sendSms(this.registrationData.phone, this.registrationData.mesg).subscribe(
      (data) => {
        Swal.fire("Success !!", "sms send", 'success');
        this.router.navigate(['/admin/voterDetails'])

      },
      (error) => {
        Swal.fire("Failure !!", error.error.message, 'error');
      }
    )
  }



}
