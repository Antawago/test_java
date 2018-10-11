import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { ViewChild } from '@angular/core';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HomePage } from '../pages/home/home';
import { TranslateService } from '@ngx-translate/core';
import { USER_PROFILE, USER_STATUS_ACT, USER_TYPE_NAN } from '../services/constants';
import { DriverTypePage } from '../pages/driver-type/driver-type';
import { RegisterInformationPage } from '../pages/register-information/register-information';
import { LoginPage } from '../pages/login/login';

@Component({
  templateUrl: 'app.html',
  queries: {
    nav: new ViewChild('content')
  }
})
export class MyApp {
  rootPage: any;
  nav: any;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, public translate: TranslateService) {
    this.translate.setDefaultLang('es');
    this.translate.use('es');
    console.log("Iniciando......");
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleLightContent();
      splashScreen.hide();
      //localStorage.clear();

      let userProfile = localStorage.getItem(USER_PROFILE);
    
      if (userProfile==null || userProfile==undefined || userProfile == "undefined" || userProfile == "null") {
        this.nav.setRoot(LoginPage);
      }else{
         
        let currentUser = JSON.parse(userProfile);
        
        if (currentUser.status == USER_STATUS_ACT) {
          this.nav.setRoot(HomePage);
        }
        if (currentUser.type == USER_TYPE_NAN) {
          this.nav.setRoot(DriverTypePage);
        } else {
          this.nav.setRoot(RegisterInformationPage);
        }
      } 

    });
  }
}

