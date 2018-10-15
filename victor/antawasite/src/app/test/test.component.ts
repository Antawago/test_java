import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AccountService } from '../service/account.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {


  evaluation: any;
  constructor(public accountService: AccountService, private route: ActivatedRoute,
    private router: Router) {

  }
  enviarValidacion() {
    console.log("uuid usuario:" + this.evaluation.user.uuid);
    let serv = this.accountService.updateParameterStatus('APR', this.evaluation.id, this.evaluation.user.uuid, "", null);
    Promise.resolve(serv).then(data => {
      let result = JSON.parse(JSON.stringify(data));
      if (result.status == 'OK') {
        this.ngOnInit();
      }

    });

  }

  ngOnInit() {
    // let  uuid= this.route.paramMap.pipe(
    //   switchMap((params: ParamMap) =>
    //     params.get('uuid'))
    // );
    let uuid = this.route.snapshot.paramMap.get('uuid');
    let serviceUser = this.accountService.getParameterTestEvaluation(uuid);
    Promise.resolve(serviceUser).then(data => {
      let obj = JSON.parse(JSON.stringify(data));
      console.log(obj);
      if (obj.status == 'OK') {
        this.evaluation = obj.response;
        console.log("this.getParameterTestEvaluation", obj.response);
      }
    });
    console.log("uuid: ", uuid);
  }

}
