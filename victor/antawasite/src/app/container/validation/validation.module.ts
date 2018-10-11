import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ValidationRoutingModule } from './validation-routing.module';
import { ValidationComponent } from './validation.component';
import { AccountService } from '../../service/account.service';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from "@angular/http";


@NgModule({
  imports: [
    HttpModule, HttpClientModule,
    CommonModule,
    ValidationRoutingModule
  ],
  declarations: [ValidationComponent],
  providers: [AccountService]
})
export class ValidationModule { }
