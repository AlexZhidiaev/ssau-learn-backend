import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/realEstates';

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  constructor(private http: HttpClient) { }

  public getRealEstates(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getRealEstate(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateRealEstate(realEstate: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + realEstate.id, realEstate);
  }

  public createRealEstate(realEstate: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL, realEstate);
  }

  public deleteRealEstate(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }

  public evaluateRealEstate(realEstate: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL + "/1", realEstate);
  }
}
