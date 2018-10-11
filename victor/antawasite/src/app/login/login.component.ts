import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router, NavigationEnd } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { User } from '../model/mode.user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  successMessage: string;
  user: User = new User();
  errorMessage: string;
  constructor(private authService: AuthService, public translate: TranslateService, public router: Router) {
    console.log(translate);
    this.translate.addLangs(['en', 'fr', 'ur', 'es', 'it', 'fa', 'de', 'zh-CHS']);
    this.translate.setDefaultLang('en');
    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/en|fr|ur|es|it|fa|de|zh-CHS/) ? browserLang : 'en');
  }


  ngOnInit() {
    console.log("cerrando session....");
    if (localStorage.getItem('currentUser')) {
      this.authService.logOut();
    }
  }

  login() {
    let data = this.authService.logIn(this.user).subscribe(data => {
      console.log("gooo!!!");
      this.successMessage = "Form submitted successfully";

      this.router.navigate(['/dashboard']);
    }, err => {
      console.log("err", err);
      this.errorMessage = err.message;
    }
    )
  }
  register() {
    this.router.navigate(['/register']);
  }

}
