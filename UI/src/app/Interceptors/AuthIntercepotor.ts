import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, tap, throwError } from "rxjs";
import { AuthService } from "../auth/services/auth.service";
import { NgxSpinnerService } from "ngx-spinner";
import { ToastrService } from "ngx-toastr";
import { Router } from "@angular/router";


@Injectable()
export class AuthIntercepotor implements HttpInterceptor {

    constructor(
      private _authService:AuthService,
      private _spinner:NgxSpinnerService,
      private _toastr:ToastrService,
      private _router:Router
    )
    {

    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let req = request;
        let res: any;
        let newToken: any;
        let token = this._authService.getToken();
        if (token != null) {
          // console.log("<-----Request Url------>",req.url)
          if(req.url.includes("/signin")){
            // req = req.clone({ setHeaders: { "Access-Control-Allow-Origin":"*" } });
          }else{
          req = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
          }
        }
        return next.handle(req).pipe(
          tap((evt) => {
            if (evt instanceof HttpResponse) {
              this._spinner.show();
              if (evt.status==200) {
                res = evt;
                if (res.headers.has("NewToken")) {
                  newToken = res.headers.get("NewToken");
                  this._authService.setToken(newToken);
                }
              }
              if (evt.status == 500) {
                this._toastr.error("Something Went Wrong", "Error");
                this._spinner.hide();
              }
              if (evt.status == 401) {
                this._router.navigate([""]);
                this._spinner.hide();
              }
            }
            if (evt instanceof HttpErrorResponse) {
              this._spinner.hide();
              alert(evt.error);
            }
          }),
          catchError((err) => this.handleError(err))
        );
      }
      private handleError(error: HttpErrorResponse): Observable<any> {
        // this.spinner.stop();
        if (error.status === 401) {
          this._toastr.error("Session Expired");
          this._authService.logOut();
          this._spinner.hide();
          return throwError(()=>error);
        }
        if (error.status === 500) {
          // this.swalService.error("Internal Server Error");
          return throwError(()=>error);
        }
        if (error.status == 404) {
          return throwError(()=>error);
        }
        if (error.status == 400) {
          return throwError(()=>error);
        }
        if (error.status == 504) {
          return throwError(()=>error);
        }
        return throwError(()=>error);
      }
    
}

