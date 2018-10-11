import { Injectable } from '@angular/core';
//import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { AppComponent } from "../app.component";
import { map } from "rxjs/operators";
import { User } from '../model/mode.user';


@Injectable()
export class AuthService {
  constructor(private http: Http) { }

  public logIn(user: User) {
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    // creating base64 encoded String from user name and password
    var base64Credential: string = btoa( user.username+ ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);
    let options = new RequestOptions();
    
    options.headers=headers;

  return this.http.get(AppComponent.API_URL + "/account/login", options).pipe(
    map((response: Response) => {
      // login successful if there's a jwt token in the response
      console.log("Respuesta: ",response.json());
      let user = response.json().response.principal;// the returned user object is a principal object
      if (user) {
        // store user details  in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
    }));

  }

  getConfig() {
    return this.http.get(AppComponent.API_URL);
  }

  logOut() {
    // remove user from local storage to log user out
    console.log("close session from service...");
    // console.log(localStorage.getItem('currentUser'));
    localStorage.clear();
    return this.http.get(AppComponent.API_URL+"/account/logout",{}).pipe(
      map((response: Response) => {
        localStorage.removeItem('currentUser');
      }));

  }
}
