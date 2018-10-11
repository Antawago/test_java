import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StepsRoutingModule } from './steps-routing.module';
import { StepsComponent } from './steps.component';
import { AccountService } from '../../../service/account.service';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    HttpModule, HttpClientModule,
    CommonModule,
    StepsRoutingModule,
    FormsModule
  ],
  declarations: [StepsComponent],
  providers: [AccountService]
})
export class StepsModule { }
