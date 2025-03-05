import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SignInComponent } from "./auth/sign-in/sign-in.component";
import { SignUpComponent } from "./auth/sign-up/sign-up.component";
import { ForgetPasswordComponent } from "./auth/forget-password/forget-password.component";
import { HomeComponent } from "./_Layout/home/home.component";





const routes:Routes=[

    {
        path:'',
        pathMatch:'full',
        redirectTo:'signUp'
    },
    // {
    //     path:'',
    //     component:SignInComponent
    // },
    {
        path:'signIn',
        component:SignInComponent
    },
    {
        path:'signUp',
        component:SignUpComponent
    },
    {
        path:'forgetPassword',
        component:ForgetPasswordComponent
    },
    {
        path:'home',
        component:HomeComponent,
        loadChildren:()=> import('./_Pages/pages.module').then(m=>m.PagesModule)
    }
];
@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})
export class AppRoutingModule {}