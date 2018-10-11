import { Injectable } from "@angular/core";
import { Observable } from 'rxjs/Observable';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import 'rxjs/add/operator/take';
import { map } from "rxjs/operators";
import { User } from "../model/user.model";
import { USER_TYPE, API_URL, WAIT } from "./constants";
import { ToolsService } from "./tools-service";
import { LoadingController } from "ionic-angular";
import { AbstractService } from "./abstract-service";

@Injectable()
export class AuthService extends AbstractService {

  user: any;


  options: any;

  constructor(public http: Http, public toolsService: ToolsService, public loadingCtrl: LoadingController) {
    super(http, toolsService, loadingCtrl);
  }

  getUserData() { // get current user data from firebase
    //return this.afAuth.auth.currentUser;
  }

  getUser(id) { // get driver by id
    // return this.db.object('drivers/' + id);
  }

  login(email, password) {
    let waitMessage = this.toolsService.getTranslatedMessage(WAIT);
    let loading = this.loadingCtrl.create({ content: waitMessage });
    loading.present();
    // creating base64 encoded String from user name and password
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    var base64Credential: string = btoa(email + ':' + password);
    headers.append("Authorization", "Basic " + base64Credential);
    let options = new RequestOptions();
    options.headers = headers;


    return new Promise((resolve, reject) => {
      this.http.get(API_URL + "/account/login/"+USER_TYPE, options).toPromise()
        .then(
          res => {
            loading.dismiss();
            return resolve(res.json())
          },
          err => {
            loading.dismiss();
            this.toolsService.processError(err);
          });
    });
  }

  logout() {
    localStorage.clear();
    //return this.afAuth.auth.signOut();   // logout from firebase
  }

  getUserInformation(uuid) {
    return this.http.get(API_URL + '/account/userinformation?uuid=' + uuid, this.options).pipe(
      map((response: Response) => response.json()));
  }



  extractData(res: Response) {
    let body = res.json();
    return body || {};
  }
  handleErrorObservable(error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }

  updateUserInformation(userInfo) {
    return this.http.post(API_URL + '/account/updateuserinformation', userInfo, this.options).pipe(
      map((response: Response) => response.json()));
  }

  // register new account
  register(userInfo: User) {
    return this.callPostService(API_URL + '/account/register?type=' + USER_TYPE, userInfo, "register(userInfo: User)");
    // this.toolsService.showWait();
    // return new Promise((resolve, reject) => {
    //   this.http.post(API_URL + '/account/register?type=' + USER_TYPE, userInfo, this.options).toPromise()
    //     .then(
    //       res => {
    //          this.toolsService.hideWait();
    //         return resolve(res.json());
    //       },
    //       err => {
    //          this.toolsService.hideWait();
    //         console.log("DriverService.register(userProfile: any) ", err)
    //         this.toolsService.processError(err);
    //       });
    // });
  }


}
