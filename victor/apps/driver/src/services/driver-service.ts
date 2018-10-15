import { Injectable } from '@angular/core';
import { AuthService } from "./auth-service";
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { map } from "rxjs/operators";
import { USER_TYPE, USER_PROFILE, API_URL, SERV_URL } from './constants';
import { ToolsService } from './tools-service';
import { AbstractService } from './abstract-service';
import { LoadingController } from 'ionic-angular';

@Injectable()
export class DriverService extends AbstractService {
  user: any;
  headers: any;
  driverProfile: any;
  options: any;


  constructor(public http: Http, public authService: AuthService, public toolsService: ToolsService, public loadingCtrl: LoadingController) {
    super(http, toolsService, loadingCtrl);
    this.user = this.authService.getUserData();
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Accept', 'application/json')
    this.options = new RequestOptions({ headers: this.headers });
  }

  /**
   * This method update driver type.
   * @param userProfile Response
   */
  updateDriveType(userProfile: any) {
    return this.callPostService(API_URL + '/account/updatetypedriver', userProfile, "  updateDriveType(userProfile: any) ");
  }


  updateDriveDocumentsAndSendValidation(document: any) {
    return this.callPostService(API_URL + '/users/updateDriveDocumentsAndSendValidation', document, " updateDriveDocumentAndSendValidate(document: any)");
  }

  setDriver(driver) {
    this.driverProfile = JSON.stringify(driver);
    //console.log("setDriver", this.driverProfile);
    localStorage.setItem(USER_PROFILE, this.driverProfile);
  }

  getDriverInfo() {
    return JSON.parse(localStorage.getItem(USER_PROFILE));//this.driverProfile;
  }

  getDriver(uuid: any) {
    return this.callGetService(API_URL + '/account/getUserProfile?type=' + USER_TYPE + '&uuid=' + uuid,"getDriver(uuid: any)");
  }

  getDriverDocumentsError(userId: any) {
    return this.callGetService(API_URL + '/validation/getDriverDocumentsError?id=' + userId ,"getDriverDocumentsError(userId: any)");
  }


  updatePositionTest(uuid: string, lat: any, lng: any, code: any) {
    console.log('updatePositionTest');
    let datos = { "driver": uuid, "lat": lat, "lng": lng, "postalCode": code };
    let options = new RequestOptions();
    options.headers = this.headers;

    return this.http.post(SERV_URL + "/drivers" + "/update", datos, options).pipe(
      map((response: Response) => {
        // login successful if there's a jwt token in the response
        let user = response.json()// the returned user object is a principal object
        console.log(user);
      }));


  }

  registerStatus(uuid: string) {
    console.log("registerStatus..............");
    let datos = { "driver": uuid };
    let options = new RequestOptions();
    options.headers = this.headers;
    return this.http.post(SERV_URL + "/drivers" + "/register", datos, options).pipe(
      map((response: Response) => response.json()));
  }

  sendToValid(id: any) {
    // let user = this.authService.getUserData();
    // return this.db.object('drivers/' + user.uid);

    return this.http.post(API_URL + '/users/sendtovalid?id=' + id, this.options).pipe(
      map((response: Response) => response.json()));
  }

  // update driver's position
  updatePosition(vehicleId, vehicleType, locality, lat, lng, rating, name) {
    // let path = 'localities/' + locality + '/' + vehicleType + '/' + vehicleId;
    // console.log('tracking', lat, lng);
    // this.db.object(path).take(1).subscribe(snapshot => {
    //   console.log(snapshot);
    //   // insert if not exists
    //   if (snapshot.$value === null) {
    //     this.db.object(path).set({
    //       lat: lat,
    //       lng: lng,
    //       oldLat: lat,
    //       oldLng: lng,
    //       last_active: Date.now(),
    //       rating: rating,
    //       name: name
    //     });

    //   } else {
    //     // update
    //     this.db.object(path).update({
    //       lat: lat,
    //       lng: lng,
    //       oldLat: snapshot.lat,
    //       oldLng: snapshot.lng,
    //       last_active: Date.now(),
    //       rating: rating,
    //       name: name
    //     });
    //   }
    // });
  }
}
