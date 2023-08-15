import { environment } from '../environments/environment';
import { Component } from '@angular/core';
import { TokenStorageService } from './svc/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private role: string = '';
  isLoggedIn = false;
  showEditor = false;
  login?: string;

  constructor(private tokenStorageService: TokenStorageService) {
    window["apiUrl"] = environment.production ? "" : "http://localhost:5555";
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.role = user.role;

      this.showEditor = this.role == 'ROLE_ADMIN';

      this.login = user.login;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    if (environment.production) {
      window.location.href = "/home";
    } else {
      window.location.reload();
    }
  }}
