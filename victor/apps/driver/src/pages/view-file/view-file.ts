import { Component } from '@angular/core';
import { NavController, NavParams, Platform, LoadingController } from 'ionic-angular';
import { File } from '@ionic-native/file';
import { FileOpener } from '@ionic-native/file-opener';
import { UPDATE_WAIT } from '../../services/constants';
import { ToolsService } from '../../services/tools-service';

@Component({
  selector: 'page-view-file',
  templateUrl: 'view-file.html',
})
export class ViewFilePage {

  image: any;
  esImagen: boolean = false;
  nombreArchivo: string = "";

  constructor(private fileOpener: FileOpener, private file: File,
     public platform: Platform, public navCtrl: NavController, 
     public navParams: NavParams, public loadingCtrl: LoadingController,public toolsService:ToolsService) {

  }


  saveAndOpenPdf(pdf: string, filename: string) {
    let loading = this.loadingCtrl.create({ content: UPDATE_WAIT });
    loading.present();

    const writeDirectory = this.platform.is('ios') ? this.file.dataDirectory : this.file.externalDataDirectory;
    this.file.writeFile(writeDirectory, filename, this.convertBaseb64ToBlob(pdf, 'data:application/pdf;base64'), { replace: true })
      .then(() => {
        loading.dismiss();
        this.fileOpener.open(writeDirectory + filename, 'application/pdf')
          .catch(() => {
            console.log('Error opening pdf file');
            loading.dismiss();
          });
      })
      .catch(() => {
        console.error('Error writing pdf file');
        loading.dismiss();
      });
  }

  private convertBaseb64ToBlob(b64Data, contentType): Blob {
    contentType = contentType || '';
    const sliceSize = 512;
    b64Data = b64Data.replace(/^[^,]+,/, '');
    b64Data = b64Data.replace(/\s/g, '');
    const byteCharacters = window.atob(b64Data);
    const byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);
      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, { type: contentType });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ViewFilePage');
    if (this.navParams.data.doc) {
      if (this.navParams.data.doc.format == 'image/jpeg') {
        this.esImagen = true;
        this.image = this.navParams.data.doc.content;
       this.toolsService.showToast("imagen");
      } else if (this.navParams.data.doc.format == 'application/pdf') {
        console.log('name', this.navParams.data.doc.name);
        this.toolsService.showToast("pdf");
        this.nombreArchivo = this.navParams.data.doc.name;
        this.saveAndOpenPdf(this.navParams.data.doc.content, this.navParams.data.doc.name);
      }
    }
  }


}
