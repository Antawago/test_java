import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from "@angular/http";


@NgModule({
  imports: [
    HttpModule, HttpClientModule,
    CommonModule,FormsModule,
    LoginRoutingModule
  ],
  declarations: [LoginComponent],
  providers: [AuthService]
})
export class LoginModule { }
