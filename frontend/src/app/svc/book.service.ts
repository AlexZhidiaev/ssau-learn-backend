import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = '/api/v1/books';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  constructor(private http: HttpClient) { }

  public getBooks(): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL);
  }

  public getBook(id: number): Observable<any> {
    return this.http.get(window["apiUrl"] + API_URL + "/" + id);
  }

  public updateBook(book: any): Observable<any> {
    return this.http.put(window["apiUrl"] + API_URL + "/" + book.id, book);
  }

  public createBook(book: any): Observable<any> {
    return this.http.post(window["apiUrl"] + API_URL, book);
  }

  public deleteBook(id: number): Observable<any> {
    return this.http.delete(window["apiUrl"] + API_URL + "/" + id);
  }
}
