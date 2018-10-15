import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TestRoutingModule } from './test-routing.module';
import { TestComponent } from './test.component';
import { AccountService } from '../service/account.service';

import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from "@angular/http";

@NgModule({
  imports: [
    HttpModule, HttpClientModule,
    CommonModule,
    TestRoutingModule
  ],
  declarations: [TestComponent],
  providers: [AccountService]
})
export class TestModule {
}
