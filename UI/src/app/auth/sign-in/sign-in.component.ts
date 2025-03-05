import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent implements OnInit{
  loginForm:FormGroup | any;
  formSubmitted: boolean = false;
  constructor(
    private _router:Router,
    private _fb:FormBuilder,
    private _toastr: ToastrService,
    private _spinner:NgxSpinnerService,
    private _authService:AuthService
  )
  {
    this.loginForm = this._fb.group({
      email:['',[Validators.required,Validators.email]],
      password:['',Validators.required],
      // rememberMe:[false]
    })
  }

  ngOnInit(): void {
    this.formSubmitted=false;
  }
  submitForm(){
    this._spinner.show();
    this.formSubmitted=true;
    if(this.loginForm.valid){
      this._authService.login(this.loginForm.value).subscribe((resp: any) => {
        this._authService.setToken(resp.respData.jwtToken)
        this._toastr.success(resp.respMsg)
        this._router.navigate(['/home/users/'])
        setTimeout(()=>{
          this._spinner.hide()
        },
        2000
      )
      },
      error => {
        this._toastr.error(error)
        this._spinner.hide()
      },
    )
    }else{
      this._spinner.hide()
    }
  }
  goToForget() {
    this._router.navigate(['/forgetPassword'],{ queryParams: { ref: 'nav' } });
  }
}
