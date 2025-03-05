import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { NgxSpinner, NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
class ImageSnippet {
  constructor(public src: string, public file: File) {}
}
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent implements OnInit{
    signUpForm:FormGroup | any;
    formSubmitted: boolean = false;
    selectedUserProfilePicture: ImageSnippet | any;
    profileImage: string | any;
    passwordMatch: boolean = false;
    passwordLength: boolean = false;
    passwordAtleastOnLetter: boolean = false;
    passwordAtleastOnCapLetter: boolean = false;
    passwordAtleastOnSpeChar: boolean = false;
    errorFound: boolean = false;
  
    constructor(
      private _fb:FormBuilder,
      private _router_:Router,
      private _toastr:ToastrService,
      private _spinner:NgxSpinnerService,
      private __authService:AuthService
    ){
      this.signUpForm=_fb.group({
        fullName:['',Validators.required],
        email:['',[Validators.required,Validators.email]],
        phone:['',Validators.required],
        password:['',Validators.required],
        cnfrmPassword:['',Validators.required],
        profilePicture: ['']
      })
    }
    ngOnInit(): void {
      this.formSubmitted=false;
    }

    onDpSelect(event: any) {
      const file: File = event.target.files[0];
      const reader = new FileReader();
      reader.addEventListener('load', (event: any) => {
        this.selectedUserProfilePicture = new ImageSnippet(
          event.target.result,
          file
        );
        reader.onloadend=()=>{
          const base64String=reader.result as string;
          this.profileImage=base64String;
          this.signUpForm.get('profilePicture')?.setValue(this.profileImage);
        }
      });
  
      reader.readAsDataURL(file);
    }

    submitForm(){
      this._spinner.show
      this.formSubmitted=true;
      if(this.signUpForm.valid && !this.errorFound){
      this.__authService.signUp(this.signUpForm.value).subscribe((res:any)=>{
        this._toastr.show(res.respMsg)
        this._router_.navigate(['/signIn'],{queryParams:{ref:'$skvkjsskjjsdjnsd'}})
        this._spinner.hide()
      },
      error=>{
        this._toastr.error(error)
        this._spinner.hide()
      }
    )
  }
    }
    checkValidat(type: string) {
      var pwd = this.signUpForm.get('password')?.value;
  
      if (
        this.signUpForm.get('password')?.value &&
        this.signUpForm.get('cnfrmPassword')?.value &&
        this.signUpForm.get('password')?.value ==
        this.signUpForm.get('cnfrmPassword')?.value
      ) {
        this.passwordMatch = true;
        // this.errorFound = false;
      } else {
        this.passwordMatch = false;
        // this.errorFound = true;
        false;
      }
      if (pwd.length < 8) {
        this.passwordLength = false;
        // this.errorFound = true;
      } else {
        this.passwordLength = true;
        // this.errorFound = false;
      }
      if (pwd.match(/[a-z]/)) {
        this.passwordAtleastOnLetter = true;
        // this.errorFound = false;
      } else {
        // this.errorFound = true;
        this.passwordAtleastOnLetter = false;
      }
      if (pwd.match(/[A-Z]/)) {
        this.passwordAtleastOnCapLetter = true;
        // this.errorFound = false;
      } else {
        this.passwordAtleastOnCapLetter = false;
        // this.errorFound = true;
      }
  
      if (pwd.match(/[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/)) {
        this.passwordAtleastOnSpeChar = true;
        // this.errorFound = false;
      } else {
        this.passwordAtleastOnSpeChar = false;
        // this.errorFound = true;
      }
  
      if (
        this.passwordMatch == true &&
        this.passwordLength == true &&
        this.passwordAtleastOnLetter == true &&
        this.passwordAtleastOnCapLetter == true &&
        this.passwordAtleastOnSpeChar == true
      ) {
        this.errorFound = false;
      } else {
        this.errorFound = true;
      }
    }
}
