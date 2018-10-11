import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContainerComponent } from './container.component';

const routes: Routes = [
  {
      path: '',
      component: ContainerComponent,
      children: [
          { path: '', redirectTo: 'dashboard', pathMatch: 'prefix' },
          { path: 'home', loadChildren: './home/home.module#HomeModule' },
          { path: 'validation', loadChildren: './validation/validation.module#ValidationModule' },
          { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
          { path: 'steps', loadChildren:'./validation/steps/steps.module#StepsModule'  }           
      ]
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainerRoutingModule { }
