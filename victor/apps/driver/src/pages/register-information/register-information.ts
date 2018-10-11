import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { UUID, OK, UPDATE_OK } from '../../services/constants';
import { ToolsService } from '../../services/tools-service';
import { DriverService } from '../../services/driver-service';
import { DriverTypePage } from '../driver-type/driver-type';
import { UserDocument } from '../../model/userDocument';

import { Camera, CameraOptions } from '@ionic-native/camera';
import { ViewFilePage } from '../view-file/view-file';
import { LoginPage } from '../login/login';


@Component({
  selector: 'page-register-information',
  templateUrl: 'register-information.html',
})
export class RegisterInformationPage {

  driverProfile: any = {};
  titulo: string;
  documentType: string;
  userDocument: any = {};
  documents: UserDocument[] = [];
  image: string = null;
  licUp: boolean = false;
  matUp: boolean = false;
  pdfFile: any;
  documentsError: any;
  canChangeType: boolean = true;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController,
    public toolsService: ToolsService, public driverService: DriverService, private camera: Camera) {
    this.licUp = false;
    this.matUp = false;
    this.documents = [];

    let promise = this.driverService.getDriver(localStorage.getItem(UUID));
    Promise.resolve(promise)
      .then((data) => {
       
        let info = JSON.parse(JSON.stringify(data));
        if (info.status == 'OK') {
          this.driverService.setDriver(info.response);
          this.driverProfile = this.driverService.getDriverInfo();
          if (this.driverProfile.status == 'DEV') {
            this.getObservarions(this.driverProfile.user.id);
          }
        }else if (info.status == 'NO_CONTENT') {
          this.navCtrl.setRoot(LoginPage);
        }
      });

  }

  private getObservarions(idUser: any) {
    let promise = this.driverService.getDriverDocumentsError(idUser);
    Promise.resolve(promise)
      .then((data) => {
        let info = JSON.parse(JSON.stringify(data));
        if (info.status == 'OK') {
          this.documentsError = info.response;
        }
      });
  }

  changeProfileStatus() {
    this.canChangeType = false;
    this.driverProfile.status = 'RGT';
  }

  getPicture(documentType) {
    let newFile: UserDocument = new UserDocument();
    newFile.type = documentType;
    newFile.format = 'image/jpeg';
    newFile.uuid = JSON.parse(localStorage.getItem(UUID));
    newFile.upk = this.driverProfile.user.id;

    let options: CameraOptions = {
      destinationType: this.camera.DestinationType.DATA_URL,
      targetWidth: 900,
      targetHeight: 900,
      quality: 50
    }
    this.camera.getPicture(options)
      .then(imageData => {
        if (documentType == 'LIC') {
          this.licUp = true;
        } else {
          this.matUp = true;
        }
        newFile.content = imageData;
        this.documents.push(newFile);
      })
      .catch(error => {
        if (documentType == 'LIC') {
          this.licUp = false;
        } else {
          this.matUp = false;
        }
        console.error(error);
      });


  }

  /**
   * 
   */
  goDriverType() {
    this.navCtrl.push(DriverTypePage, { validar: false });
  }

  /**
   * 
   */
  ionViewDidLoad() {
    console.log("ionViewDidLoad");
    //console.log('ionViewDidLoad RegisterInformationPage', this.driverProfile);
    if (this.driverProfile.type == 'DO') {
      this.titulo = "ONLY_DRIVER";
    } else {
      this.titulo = "OWNER_DRIVER";
    }
  }

  chooseDocs(documentType) {
    this.documentType = documentType;
    
    document.getElementById('docsUpload').click();
  }

  /**
   * 
   * @param event 
   */
  uploadDocs(event) {
    let newFile: UserDocument = new UserDocument();
    let message = this.toolsService.getTranslatedMessage("P_WAIT");
    let loading = this.loadingCtrl.create({ content: message });
    loading.present();
    if (event.target.files && event.target.files[0]) {
      if (event.target.files[0].type != 'application/pdf') {
        if (this.documentType == 'LIC') {
          this.licUp = false;
        } else {
          this.matUp = false;
        }
        this.toolsService.showAlert("Formato no soportado, use un archivo .PDF");
        loading.dismiss();
        return;
      }
      
      newFile.type = this.documentType;  
      newFile.upk = this.driverProfile.user.id;
      newFile.format = event.target.files[0].type;
      newFile.name = event.target.files[0].name;
      newFile.uuid = JSON.parse(localStorage.getItem(UUID));
      
      newFile.size = event.target.files[0].size;
      
      console.log("size ", event.target.files[0].size);
      console.log("type ", event.target.files[0].type);
      console.log("name ", event.target.files[0].name);

      let reader = new FileReader();

      reader.onload = (event: any) => {
        this.pdfFile = event.target.result;
        newFile.content = event.target.result;
        if (this.documentType == 'LIC') {
          this.licUp = true;
        } else {
          this.matUp = true;
        }
      }
      reader.readAsDataURL(event.target.files[0]);
      let fileList: FileList = event.target.files;
      let file: File = fileList[0];
      console.log(file);
      // Create a root reference
      // let storageRef = firebase.storage().ref();
     
      this.documents.push(newFile);
      this.toolsService.showToast('UPLOAD_FILE');
      loading.dismiss();

      document.getElementById('formDocUpdate').click();
    }



    // let storageRef = firebase.storage().ref();
    // let loading = this.loadingCtrl.create({ content: 'Please wait...' });
    // loading.present();
    // for (let selectedFile of [(<HTMLInputElement>document.getElementById('docsPDF')).files[0]]) {
    //   let path = '/users/' + Date.now() + `${selectedFile.name}`;
    //   let iRef = storageRef.child(path);
    //   iRef.put(selectedFile).then((snapshot) => {
    //     loading.dismiss();
    //     this.user.docsURL = snapshot.downloadURL;
    //   });
    // }
  }

  openValidation() {
    // var iab = cordova.InAppBrowser;
  }
  sendValid() {
    if (this.validateDocuments()) {
      console.log("DOcumentos: ", this.documents);
      let promise = this.driverService.updateDriveDocumentsAndSendValidation(this.documents);
      Promise.resolve(promise)
        .then((data) => {
          let result = JSON.parse(JSON.stringify(data));
          if (result.status == OK) {
            this.toolsService.showToast(UPDATE_OK);
            this.documents = [];
            this.licUp = false;
            this.matUp = false;
            console.log(result);
            this.navCtrl.setRoot(RegisterInformationPage);
          } else {
            this.toolsService.showAlert(result.message);
          }
        });
    }
  }

  validateDocuments() {
    let docs = this.documents.length;
    //OD Only driver
    if (this.driverProfile.type == 'OD') {
      if (docs == 1) {
        return true;
      } else {
        this.toolsService.showToast('Favor registre documentos');
      }
    } else {
      if (docs == 2) {
        return true;
      } else {
        this.toolsService.showToast('Favor registre documentos');
      }
    }
    return false;
  }
  /**
   * 
   * @param type 
   */
  viewFile(type) {
    let doc4View: any;
    for (let document of this.documents) {
      if (document.type == type) {
        doc4View = document;
      }
    }
    this.navCtrl.push(ViewFilePage, { doc: doc4View });
    console.log("Formato documento", doc4View.format)
  }
  /**
   * 
   * @param type 
   */
  deleteFile(type) {
    //myArray.splice(index, 1);
    for (let _i = 0; _i < this.documents.length; _i++) {
      let doc = this.documents[_i];
      if (doc.type == type) {
        if (doc.type == 'LIC') {
          this.licUp = false;
        } else {
          this.matUp = false;
        }
        this.documents.splice(_i, 1);

        this.toolsService.showToast("Archivo eliminado...");
      }
    }
  }

}
