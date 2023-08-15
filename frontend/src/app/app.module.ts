import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { ShelfComponent } from './shelf/shelf.component';
import { EditorComponent } from './editor/editor.component';

import { ClientComponent } from './client/client.component';
import { RealtorComponent } from './realtor/realtor.component';
import { RealEstateComponent } from './realEstate/realEstate.component';
import { DealTypeComponent } from './dealType/dealType.component';
import { DealComponent } from './deal/deal.component';

import { authInterceptorProviders } from './hlp/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    ShelfComponent,
    EditorComponent,
    ClientComponent,
    RealtorComponent,
    RealEstateComponent,
    DealTypeComponent,
    DealComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
