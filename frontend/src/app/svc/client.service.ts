import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/clients';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http: HttpClient) { }

  public getClients(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getClient(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateClient(client: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + client.id, client);
  }

  public createClient(client: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL, client);
  }

  public deleteClient(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }
}
