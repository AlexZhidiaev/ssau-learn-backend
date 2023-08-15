import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { ShelfComponent } from './shelf/shelf.component';
import { EditorComponent } from './editor/editor.component';

import { ClientComponent } from './client/client.component';
import { RealtorComponent } from './realtor/realtor.component';
import { RealEstateComponent } from './realEstate/realEstate.component';
import { DealTypeComponent } from './dealType/dealType.component';
import { DealComponent } from './deal/deal.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'shelf', component: ShelfComponent },
  { path: 'editor', component: EditorComponent },
  { path: 'client', component:ClientComponent},
  { path: 'realtor', component:RealtorComponent},
  { path: 'realEstate', component:RealEstateComponent},
  { path: 'dealType', component:DealTypeComponent},
  { path: 'deal', component:DealComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
