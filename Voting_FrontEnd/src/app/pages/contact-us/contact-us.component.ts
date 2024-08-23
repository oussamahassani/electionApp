import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/login/login.service'
import Swal from 'sweetalert2';

@Component({
    selector: 'app-contact-us',
    templateUrl: './contact-us.component.html',
    styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {
    contactForm!: FormGroup;
    message!: string;
    constructor(private LoginService: LoginService) { }

    ngOnInit(): void {
        this.contactForm = new FormGroup({
            name: new FormControl(null, [Validators.required]),
            email: new FormControl(null, [Validators.required, Validators.email]),
            phone: new FormControl(null, [Validators.required]),
            message: new FormControl(null, [Validators.required])

        });
    }

    onSubmit() {
        this.LoginService.contactUs(this.contactForm.value).subscribe((response: any) => {
            next: (data: any) => {
                this.message = data;

                //   this.router.navigate(['/home'])
            }
        },

            (error: any) => {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'cannot send your email, adrese not valid ',
                    showConfirmButton: false,
                    timer: 9500,
                });
            })
    }
}