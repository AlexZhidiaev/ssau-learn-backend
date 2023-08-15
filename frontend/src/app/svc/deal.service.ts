import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/deals';

@Injectable({
  providedIn: 'root'
})
export class DealService {
  constructor(private http: HttpClient) { }

  public getDeals(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getDeal(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateDeal(deal: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + deal.id, deal);
  }

  public createDeal(deal: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL,deal);
  }

  public deleteDeal(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }
}
