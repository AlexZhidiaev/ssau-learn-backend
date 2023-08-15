import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/health/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + 'public', { responseType: 'text' });
  }

  getRegistered(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + 'registered', { responseType: 'text' });
  }

  getPrivate(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + 'private', { responseType: 'text' });
  }
}
