import { Component, OnInit } from '@angular/core';
import { NavController, LoadingController, AlertController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { USER_PROFILE, UUID } from "../../services/constants";
import { AuthService } from "../../services/auth-service";
import { TranslateService } from '@ngx-translate/core';
import { User } from '../../model/user.model';
import { ToastController } from 'ionic-angular';
import { ToolsService } from '../../services/tools-service';
import { DriverTypePage } from '../driver-type/driver-type';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html'
})
export class RegisterPage implements OnInit {

  userInfo: User;
  registerForm: FormGroup;
  username: AbstractControl;
  password: AbstractControl;
  names: AbstractControl;
  lastNames: AbstractControl;
  cellphone: AbstractControl;

  constructor(public toastCtrl: ToastController, public nav: NavController, public authService: AuthService,
    public alertCtrl: AlertController, public loadingCtrl: LoadingController, public translate: TranslateService,
    public formBuilder: FormBuilder, public toolsService: ToolsService) {
    this.registerForm = formBuilder.group({
      names: ['', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.minLength(2)])],
      lastNames: ['', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.minLength(2)])],
      cellphone: ['', Validators.compose([Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern('[0-9]{10}')])],
      username: ['', Validators.compose([Validators.required, Validators.email, Validators.minLength(8), Validators.maxLength(30)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });
  }

  ngOnInit() {
    this.userInfo = new User();
  }

  signup(value: any) {
    console.log("registrando");
    this.userInfo.username = value.username;
    this.userInfo.password = value.password;
    this.userInfo.names = value.names;
    this.userInfo.lastNames = value.lastNames;
    this.userInfo.cellphone = value.cellphone;

    let promise = this.authService.register(this.userInfo);
    Promise.resolve(promise)
      .then((data) => {
        let result = JSON.parse(JSON.stringify(data));
        if (result.status == 'OK') {
          this.toolsService.showAlert('REGISTER_OK');
          localStorage.setItem(USER_PROFILE, JSON.stringify(result.response));
          localStorage.setItem(UUID, JSON.stringify(result.response.user.uuid));
          this.nav.setRoot(DriverTypePage);
        } else {
          this.toolsService.showAlert(result.message);
        }
      });
  }

  login() {
    this.nav.setRoot(LoginPage);
  }
}
