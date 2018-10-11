import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Geolocation } from '@ionic-native/geolocation';
import { IonicStorageModule } from '@ionic/storage';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';
import { DriverTypePage } from '../pages/driver-type/driver-type';
import { RegisterInformationPage } from '../pages/register-information/register-information';
import { ViewFilePage } from '../pages/view-file/view-file';
import { HttpModule } from '@angular/http';
import { DriverService } from '../services/driver-service';
import { AuthService } from '../services/auth-service';
import { ToolsService } from '../services/tools-service';
import { Camera } from '@ionic-native/camera';
import { File } from '@ionic-native/file';
import { FileOpener } from '@ionic-native/file-opener';
import { AbstractService } from '../services/abstract-service';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/lang/', '.json');
}

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterPage,
    DriverTypePage,
    RegisterInformationPage,
    ViewFilePage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    BrowserModule,
    HttpModule,
    IonicStorageModule.forRoot(),
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    RegisterPage,
    DriverTypePage,
    RegisterInformationPage,
    ViewFilePage
  ],
  providers: [
    AbstractService,
    StatusBar,
    SplashScreen,
    Geolocation,
    DriverService,
    AuthService,
    ToolsService,
    Camera,
    File,
    FileOpener,
    /* import services */
    { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
