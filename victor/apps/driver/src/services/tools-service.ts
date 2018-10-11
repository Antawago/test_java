import { Injectable } from "@angular/core";
import { TranslateService } from '@ngx-translate/core';
import { AlertController, ToastController, LoadingController } from 'ionic-angular';
import { UPDATE_WAIT, NO_CONNECT } from "./constants";

@Injectable()
export class ToolsService {

    constructor(private translate: TranslateService, public loadingCtrl: LoadingController,
        private alertCtrl: AlertController, public toastCtrl: ToastController) {
        console.log("ToolsService");
    }


    /**
     * 
     * @param message 
     */
    public showAlert(message) {
        let alert = this.alertCtrl.create({
            message: this.getTranslatedMessage(message),
            buttons: ['OK']
        });
        alert.present();
    }

    public processError(err: any, methodName?: string) {
        console.log("processError.processError([" + methodName + "]) ", err);
        console.log("ToolsService.processError(ok) ", err.ok);
        console.log("ToolsService.processError(status) ", err.status);
        console.log("ToolsService.processError(statusText) ", err.statusText);
        console.log("ToolsService.processError(type) ", err.type);
        console.log("ToolsService.processError(url) ", err.url);

        switch (err.status) {
            case 0: {
                this.showAlert(NO_CONNECT);
                break;
            }
            case 401: {
                this.showAlert('BAD_CREDENTIALS');
                break;
            }
            default: {
                let erro = JSON.parse(err._body);
                console.log("ToolsService.processError(UNDEFINED_ERROR)", erro.message);
                this.showAlert(erro.message);
                break;
            }
        }

    }

    /**
     * 
     * @param message 
     */
    public showToast(message) {
        const toast = this.toastCtrl.create({
            message: this.getTranslatedMessage(message),
            duration: 3000,
            position: 'bottom'//top,bottom,middle
        });
        toast.present();
    }

    /**
     * 
     * @param message 
     */
    public getTranslatedMessage(message) {
        this.translate.get(message).subscribe((text: string) => {
            message = text + " ";
        }, err => {
            message = message;
        });
        return message;
    }

    /**
     * 
     */
    public getLoadingCtrl() {
        let loading = this.loadingCtrl.create({ content: this.getTranslatedMessage(UPDATE_WAIT) });
        loading.present();
        return loading;
    }
}
