import { Component } from '@angular/core';
import { NavController, AlertController, LoadingController, ToastController } from 'ionic-angular';
import { RegisterPage } from '../register/register';
import { AuthService } from "../../services/auth-service";
import { ENABLE_SIGNUP, UUID, USER_PROFILE } from "../../services/constants";
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ToolsService } from '../../services/tools-service';
import { DriverTypePage } from '../driver-type/driver-type';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  userInfo: any = {};
  isRegisterEnabled = ENABLE_SIGNUP;
  authForm: FormGroup;
  username: AbstractControl;
  password: AbstractControl;
  errorMessage: String;

  constructor(public nav: NavController, public authService: AuthService,
    public alertCtrl: AlertController, public loadingCtrl: LoadingController,
    public toast: ToastController, public translate: TranslateService,
    public formBuilder: FormBuilder, public toolsService: ToolsService) {
    localStorage.clear();
    this.authForm = formBuilder.group({
      email: ['', Validators.compose([Validators.required, Validators.email, Validators.minLength(8), Validators.maxLength(30)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });
  }

  // go to signup page
  signup() {
    this.nav.push(RegisterPage);
  }

  // go to login page
  login(value: any) {
    let promise = this.authService.login(value.email, value.password);
    Promise.resolve(promise)
      .then((data) => {
        let profile = JSON.parse(JSON.stringify(data)).response;
        localStorage.setItem(USER_PROFILE, JSON.stringify(profile));
        console.log("user USER_PROFILE> ", localStorage.getItem(USER_PROFILE));
        localStorage.setItem(UUID, profile.user.uuid);
        console.log("user UUID> ", localStorage.getItem(UUID));
        this.nav.setRoot(DriverTypePage);
      });
  }

  reset() {
    // if (this.username) {
    //   firebase.auth().sendPasswordResetEmail(this.username)
    //     .then(data =>
    //       this.toast.create({ message: 'Please check your mail', duration: 3000 }).present())
    //     .catch(err => this.toast.create({ message: err.message, duration: 3000 }).present())
    // }
  }

}