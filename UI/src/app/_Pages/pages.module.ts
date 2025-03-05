import { NgModule }  from '@angular/core';
import { UsersComponent } from './users/users.component';
import { CommonModule } from '@angular/common';
import { PagesRoutingModule } from './pages-routing-module';
import { DataService } from '../auth/services/data.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthIntercepotor } from '../Interceptors/AuthIntercepotor';



@NgModule({
    declarations:[
        UsersComponent
    ],
    imports:[
        CommonModule,
        PagesRoutingModule,
    ],
    exports:[

    ],
    providers:[
        DataService,
        // {
        //     provide: HTTP_INTERCEPTORS,
        //     useClass: AuthIntercepotor,
        //     multi: true
        //   },
    ]
})
export class PagesModule {}