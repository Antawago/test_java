import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContainerRoutingModule } from './container-routing.module';
import { ContainerComponent } from './container.component';
import { HeaderComponent } from './components/header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
  imports: [
    CommonModule,
    ContainerRoutingModule
  ],
  declarations: [ContainerComponent, HeaderComponent, SidebarComponent, NavbarComponent]
})
export class ContainerModule { }
