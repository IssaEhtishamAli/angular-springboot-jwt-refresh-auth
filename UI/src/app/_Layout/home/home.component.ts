import { Component, OnInit } from '@angular/core'
import { AuthService } from '../../auth/services/auth.service';




@Component({
    selector:'app-home',
    templateUrl:'./home.component.html',
    styleUrl:'./home.component.scss'
})
export class HomeComponent implements OnInit{
    menu_item:any;
    constructor(
      private _authService:AuthService,
    ) {}
    ngOnInit(): void {
    }
    logout(){
      this._authService.logOut();
    }
}