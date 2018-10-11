import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { Router } from '@angular/router';

@Injectable()
export class AuthorizerGuard implements CanActivate {
  constructor(private router: Router) {}
  canActivate(    next: ActivatedRouteSnapshot,    state: RouterStateSnapshot) {
    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      console.log("tiene:::::::::::::::::::::.");
      console.log(localStorage.getItem('currentUser'));
      console.log(state.url); 
      return true;
    }

    console.log("NONONONO tiene:::::::::::::::::::::.");
    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    return false;
  }
}
