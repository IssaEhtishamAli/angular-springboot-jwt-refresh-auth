import { NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SideNavComponent } from './sidenav/sidenav.component';
import { CommonModule } from '@angular/common';
import { LayoutRoutingModule } from './layout-routing-module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthIntercepotor } from '../Interceptors/AuthIntercepotor';




@NgModule({
    declarations:[
        HeaderComponent,
        HomeComponent,
        SideNavComponent
    ],
    imports:[
        CommonModule,
        LayoutRoutingModule
    ],
    exports:[
       
    ],
    providers:[
        // {
        //     provide:HTTP_INTERCEPTORS,
        //     useClass:AuthIntercepotor,
        //     multi:true
        // }
    ]
})
export class LayoutModule {}