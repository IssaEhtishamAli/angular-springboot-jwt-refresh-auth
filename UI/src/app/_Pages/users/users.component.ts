import { Component, OnInit } from '@angular/core'
import { DataService } from '../../auth/services/data.service';
import { NgxSpinnerService } from 'ngx-spinner';



@Component({
    selector:'app-users',
    templateUrl:'./users.component.html',
    styleUrl:'./users.component.scss'
})
export class UsersComponent implements OnInit{
     menuitems:any
     data: any[] = [];
     totalItems = 0;
     currentPage = 1;
     pageSize = 10;
   
     constructor(private dataService: DataService,private _spinner:NgxSpinnerService,
     ) { }
   
     ngOnInit(): void {
       this.loadData();
     }
   
     loadData(): void {
       this.dataService.getData(this.currentPage, this.pageSize).subscribe((response:any) => {
        this._spinner.hide();
         this.data = response.content;
        //  this.totalItems = response.content.length;
       });
     }
   
     onPageChange(page: number): void {
       this.currentPage = page;
       this.loadData();
     }   
}