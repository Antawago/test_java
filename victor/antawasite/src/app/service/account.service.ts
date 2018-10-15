import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { map } from "rxjs/operators";
import { AppComponent } from '../app.component';

@Injectable()
export class AccountService {
  headers: any;
  options: any;

  constructor(private http: Http) {
    this.headers = new Headers({ 'Content-Type': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  getDriversForValidation() {
    return this.callGetService('/validation/driverstovalidate', 'getDriversForValidation');
  }

  getDocumentDriver(id: any) {
    return this.callGetService('/validation/getDocumentDriver?id=' + id, "getDocumentDriver(id: any)");
  }

  getUser(uuid: any) {
    return this.callGetService('/account/getUser?uuid=' + uuid, "getUser(uuid: any)");
  }

  getParameterTestEvaluation(uuid: any) {
    return this.callGetService('/validation/getTestEvaluationParameter?uuid=' + uuid, "getParameterTestEvaluation(uuid: any)");
  }



  getDocumentsContent(id: any) {
    return this.callGetService('/validation/getDocumentsContent?id=' + id, "getDocumentsContent(id: any)");
  }

  getValidationDriver(id: any) {
    return this.callGetService('/validation/getValidationDriver?id=' + id, "getValidationDriver(id: any)");
  }

  updateDocumentStatus(status: any, idDoc: any, uuid: any, comment: any, upk: any) {
    let doc = { "idDoc": idDoc, "status": status, "uuid": uuid, "comment": comment, "upk": upk };
    return this.callPostService("/validation/updateDocumentStatus", doc, "updateDocumentStatus(status:any,idDoc:any)");
  }

  updateStatusUserProfile(userProfile:any) {
    return this.callPostService("/users/updateProfileStatus", userProfile, "updateStatusUserProfile(userProfile:any)");
  }

  updateParameterStatus(status: any, idDoc: any, uuid: any, comment: any, upk: any) {
    let doc = { "idDoc": idDoc, "status": status, "uuid": uuid, "comment": comment, "upk": upk };
    return this.callPostService("/validation/updateParameterStatus", doc, "updateParameterStatus(status:any,idDoc:any)");
  }

  public callPostService(url: string, data?: any, callMethod?: string) {
    return new Promise((resolve, reject) => {
      this.http.post(AppComponent.API_URL + url, data, this.options).toPromise()
        .then(res => {
          return resolve(res.json())
        },
          err => {
            console.log(callMethod, err);
          });
    });
  }

  private callGetService(url: string, method: any) {
    return new Promise((resolve, reject) => {
      this.http.get(AppComponent.API_URL + url, this.options).toPromise()
        .then(res => resolve(res.json()),
          err => {
            console.log("callGetService =>" + method, err);
          });
    });
  }
}
