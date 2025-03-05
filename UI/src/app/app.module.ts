import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ForgetPasswordComponent } from "./auth/forget-password/forget-password.component";
import { SignUpComponent } from "./auth/sign-up/sign-up.component";
import { CommonModule } from "@angular/common";
import { SignInComponent } from "./auth/sign-in/sign-in.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AuthService } from "./auth/services/auth.service";
import { AuthIntercepotor } from "./Interceptors/AuthIntercepotor";
import { NgxSpinnerModule } from "ngx-spinner";
import { ToastrModule } from "ngx-toastr";
import { LayoutModule } from "./_Layout/layout.module";





@NgModule({
    declarations:[
        AppComponent,
        ForgetPasswordComponent,
        SignUpComponent,
        SignInComponent
    ],
    imports:[
        BrowserAnimationsModule,
        BrowserModule,
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
        NgxSpinnerModule,
        ToastrModule.forRoot({
          preventDuplicates: true
        }),
        AppRoutingModule,
        LayoutModule
    ],
    exports:[],
    providers:[
        AuthService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthIntercepotor,
            multi: true
          },
    ],
    bootstrap:[AppComponent]
})
export class AppModule {}