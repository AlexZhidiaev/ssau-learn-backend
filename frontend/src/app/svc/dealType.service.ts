import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/dealTypes';

@Injectable({
  providedIn: 'root'
})
export class DealTypeService {
  constructor(private http: HttpClient) { }

  public getDealTypes(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getDealType(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateDealType(dealType: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + dealType.id, dealType);
  }

  public createDealType(dealType: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL, dealType);
  }

  public deleteDealType(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }
}
