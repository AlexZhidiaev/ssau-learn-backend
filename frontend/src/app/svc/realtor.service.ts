import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/realtors';

@Injectable({
  providedIn: 'root'
})
export class RealtorService {
  constructor(private http: HttpClient) { }

  public getRealtors(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getRealtor(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateRealtor(realtor: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + realtor.id, realtor);
  }

  public createRealtor(realtor: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL, realtor);
  }

  public deleteRealtor(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }
}
