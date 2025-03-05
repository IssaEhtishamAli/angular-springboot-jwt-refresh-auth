import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.dev';

@Injectable()
export class DataService {


  constructor(private http: HttpClient) { }

  getData(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<any>(`${environment.base_url}admin/userspage?`, { params });
  }
}
