import { Component, OnInit } from '@angular/core';
import { Data } from '../../../providers/data';
import { AccountService } from '../../../service/account.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';


@Component({
    selector: 'app-steps',
    templateUrl: './steps.component.html',
    styleUrls: ['./steps.component.css']
})
export class StepsComponent implements OnInit {
    user: any;
    profile: any;
    image: any;
    document: any;
    documents: any;
    validations: any;
    radioModel: any;
    idUserProfile: any;
    noAceptList: any = ["Archivo no legible", "Archivo dañado", "No corresponde al dato solicitado"];
    viewImage: boolean = false;
    viewDocument: boolean = false;

    constructor( public router: Router,private accountService: AccountService, private data: Data, private sanitizer: DomSanitizer) { }

    ngOnInit() {
        let infoJson = JSON.parse(JSON.stringify(this.data.storage));
        this.idUserProfile = infoJson.id;
        this.user = infoJson.user;
        this.profile = infoJson.profile;
        console.log("StepsComponent ", this.idUserProfile);
        let serv = this.accountService.getValidationDriver(this.user.id);
        Promise.resolve(serv).then(data => {

            console.log("data ", data);
            let dataJSON = JSON.parse(JSON.stringify(data));
            this.documents = dataJSON.response.documents;
            this.validations = dataJSON.response.userEvaluations;

        });

    }


    validUser(): void {
        let infoJson = JSON.parse(JSON.stringify(this.data.storage));
        infoJson.status='ACT';
        let serv = this.accountService.updateStatusUserProfile(infoJson);
        Promise.resolve(serv).then(data => {
            console.log(data);
            this.router.navigate(['/validation']);
        });

    }



    validation(valid) {

    }

    changeStatusDocument(status: any, doc: any) {
        //PRE por evaluar
        console.log("changeStatusDocument()", doc.comment);
        if (status != 'PRE') {
            let serv = this.accountService.updateDocumentStatus(status, doc.id, this.user.uuid, doc.comment, this.idUserProfile);
            Promise.resolve(serv).then(data => {
                let result = JSON.parse(JSON.stringify(data));
                if (result.status == 'OK') {
                    this.ngOnInit();
                }

            });
        }
        console.log(status + " --> " + doc.id);
    }

    getDocumentsContent(doc: any) {
        let serv = this.accountService.getDocumentsContent(doc.id);
        this.viewImage = false;
        this.viewDocument = false;
        Promise.resolve(serv).then(data => {
            let result = JSON.parse(JSON.stringify(data));
            console.log("doc.formatFile ", doc.formatFile);
            if (result.status == 'OK') {
                if ('image/jpeg' == doc.formatFile) {
                    this.image = result.response;
                    this.viewImage = true;
                } else {
                    this.document = "<object style=\"width:100%;height:300px;\" data=\"data:application/pdf;base64," + result.response + "\" type=\"application/pdf\"></object>";
                    this.viewDocument = true;
                }
            }
        });
    }

    changeStatusDocumentTmp(status: any, doc: any) {
        doc.comment = "";
        doc.status = status;
    }

    changeStatusParameter(status: any, idDoc: any) {
        let serv = this.accountService.updateParameterStatus(status, idDoc, this.user.uuid, "", this.idUserProfile);
        Promise.resolve(serv).then(data => {
            let result = JSON.parse(JSON.stringify(data));
            if (result.status == 'OK') {
                this.ngOnInit();
            }

        });
        console.log(status + " --> " + idDoc);
    }

    openFile(idDoc: any) {
        console.log(" idDoc --> " + idDoc);
        //window.open(`data:application/pdf;base64, ${response}`, '_self');
    }
}
