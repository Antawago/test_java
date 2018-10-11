import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../service/account.service';
import { Router, } from '@angular/router';
import { Data } from '../../providers/data';

declare interface TableData {
  headerRow: string[];
  dataRows: string[][];
}

@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.css']
})
export class ValidationComponent implements OnInit {

  public validationList: any;
  constructor(private accountService: AccountService, public router: Router, private data: Data) { }

  ngOnInit() {
    let pro = this.accountService.getDriversForValidation();
    Promise.resolve(pro).then(data => {
      this.validationList = JSON.parse(JSON.stringify(data)).response;
    });
  }

  validateUser(userProfile) {
    console.log("userProfile", userProfile);
    this.data.storage = userProfile;
    this.router.navigate(['/steps'], { skipLocationChange: true });
      }

}
