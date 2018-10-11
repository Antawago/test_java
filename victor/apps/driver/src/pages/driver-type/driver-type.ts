import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { ToolsService } from '../../services/tools-service';
import { DriverService } from '../../services/driver-service';
import { USER_PROFILE, OK, USER_TYPE_NAN, UPDATE_OK, UUID } from '../../services/constants';
import { RegisterInformationPage } from '../register-information/register-information';
import { LoginPage } from '../login/login';


@Component({
  selector: 'page-driver-type',
  templateUrl: 'driver-type.html',
})
export class DriverTypePage {

  driverType: any;

  driverProfile: any;

  validar: boolean;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController,
    public toolsService: ToolsService, public driverService: DriverService) {

    this.validar = true;
    if (navParams.data.validar != null) {
      this.validar = navParams.data.validar;
    }
    let userProfile = localStorage.getItem(USER_PROFILE);

    if (userProfile == null || userProfile == undefined || userProfile == "undefined" || userProfile == "null") {
      this.navCtrl.setRoot(LoginPage);
      return;
    }

    let promise = this.driverService.getDriver(localStorage.getItem(UUID));
    Promise.resolve(promise)
      .then((data) => {
        this.driverService.setDriver(JSON.parse(JSON.stringify(data)).response);
        this.setDriverType();
      });
  }

  ionViewDidLoad() {
  }

  private setDriverType() {
    this.driverProfile = JSON.parse(localStorage.getItem(USER_PROFILE));
    console.log(" this.driverProfile", this.driverProfile);
    this.driverType = this.driverProfile.type;
    if (this.validar && this.driverType && this.driverType != USER_TYPE_NAN) {
      this.navCtrl.setRoot(RegisterInformationPage);
    }
  }

  saveType() {
    if (this.driverType && this.driverType == USER_TYPE_NAN) {
      this.toolsService.showAlert('DRIVER_TYPE_RQ');
      return null;
    }

    this.driverProfile.type = this.driverType;
    let promise = this.driverService.updateDriveType(this.driverProfile);
    Promise.resolve(promise)
      .then((data) => {

        let result = JSON.parse(JSON.stringify(data));
        if (result.status == OK) {
          localStorage.setItem(USER_PROFILE, JSON.stringify(this.driverProfile));
         
          this.toolsService.showToast(UPDATE_OK);
          this.navCtrl.setRoot(RegisterInformationPage);
        } else {
          this.toolsService.showAlert(result.message);
        }
      });


  }

}
