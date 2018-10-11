import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/take';
import { ToolsService } from "./tools-service";
import { LoadingController } from "ionic-angular";
import { WAIT } from "./constants";


@Injectable()
export class AbstractService {
    options: any;
    waitMessage = "";
    constructor(public http: Http, public toolsService: ToolsService, public loadingCtrl: LoadingController) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        this.options = new RequestOptions({ headers: headers });
       
    }

    public callPostService(mapping: string, data?: any, callMethod?: string) {
        this.waitMessage = this.toolsService.getTranslatedMessage(WAIT);
        
        let loading = this.loadingCtrl.create({ content: this.waitMessage });
        loading.present();
        return new Promise((resolve, reject) => {
            this.http.post(mapping, data, this.options).toPromise()
                .then(res => {
                    loading.dismiss();
                    return resolve(res.json())
                },
                    err => {
                        loading.dismiss();
                        this.toolsService.processError(err, callMethod);
                    });
        });
    }

    public callGetService(mapping: string, callMethod?: string) {
        this.waitMessage = this.toolsService.getTranslatedMessage(WAIT);
        let loading = this.loadingCtrl.create({ content: this.waitMessage });
        loading.present();
        return new Promise((resolve, reject) => {
            this.http.get(mapping, this.options).toPromise()
                .then(res => {
                    loading.dismiss();
                    return resolve(res.json())
                },
                    err => {
                        loading.dismiss();
                        this.toolsService.processError(err, callMethod);
                    });
        });
    }
}