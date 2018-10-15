import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthorizerGuard } from './shared/guard/authorizer.guard';

const routes: Routes = [
  { path: '', loadChildren: './container/container.module#ContainerModule', canActivate: [AuthorizerGuard] },
  { path: 'login', loadChildren: './login/login.module#LoginModule' },
  { path: 'test/:uuid', loadChildren: './test/test.module#TestModule' },
  { path: 'register', loadChildren: './register/register.module#RegisterModule' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
