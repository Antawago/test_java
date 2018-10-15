import { Component } from '@angular/core';
import { NavController, ModalController, AlertController, Platform } from 'ionic-angular';
import { DriverService } from '../../services/driver-service';
import { POSITION_INTERVAL, PLAY_AUDIO_ON_REQUEST, AUDIO_PATH, WELCOME } from "../../services/constants";
import { Geolocation } from '@ionic-native/geolocation';
import { TranslateService } from '@ngx-translate/core';
import { Diagnostic } from '@ionic-native/diagnostic';

declare var google: any;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  map: any;
  driver: any;
  deal: any;
  dealSubscription: any;
  isDriverAvailable: any = false;
  positionTracking: any;
  dealStatus: any = false;
  wellcomeOk: any;
  isAvailable:any;


  public job: any;
  //public remainingTime = DEAL_TIMEOUT;

  constructor(public nav: NavController, public driverService: DriverService, public modalCtrl: ModalController,
    public alertCtrl: AlertController, public geolocation: Geolocation, public translate: TranslateService,
    private diagnostic: Diagnostic, public platform: Platform) {
    this.wellcomeOk = localStorage.getItem(WELCOME);
    if (!this.wellcomeOk) {
      this.wellcomeOk = "FALSE";
    }
    console.log("HomePage: ", this.wellcomeOk)
  }

  checkLocation() {
    this.platform.ready().then((readySource) => {
      this.diagnostic.isLocationEnabled().then(
        (isAvailable) => {
          console.log('Is available? ' + isAvailable);
          alert('Is available? ' + isAvailable);
          this.isAvailable=isAvailable;
        }).catch((e) => {
          console.log(e);
          alert(JSON.stringify(e));
        });


    });
  }

  wellcomeAcept() {
    localStorage.setItem(WELCOME, "TRUE");
    this.wellcomeOk = "TRUE";
  }

  loadMap(lat, lng) {

    let styledMapType = new google.maps.StyledMapType(
      [
        { elementType: 'geometry', stylers: [{ color: '#242f3e' }] },
        { elementType: 'labels.text.stroke', stylers: [{ color: '#242f3e' }] },
        { elementType: 'labels.text.fill', stylers: [{ color: '#746855' }] },
        {
          featureType: 'administrative.locality',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#d59563' }]
        },
        {
          featureType: 'poi',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#d59563' }]
        },
        {
          featureType: 'poi.park',
          elementType: 'geometry',
          stylers: [{ color: '#263c3f' }]
        },
        {
          featureType: 'poi.park',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#6b9a76' }]
        },
        {
          featureType: 'road',
          elementType: 'geometry',
          stylers: [{ color: '#38414e' }]
        },
        {
          featureType: 'road',
          elementType: 'geometry.stroke',
          stylers: [{ color: '#212a37' }]
        },
        {
          featureType: 'road',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#9ca5b3' }]
        },
        {
          featureType: 'road.highway',
          elementType: 'geometry',
          stylers: [{ color: '#746855' }]
        },
        {
          featureType: 'road.highway',
          elementType: 'geometry.stroke',
          stylers: [{ color: '#1f2835' }]
        },
        {
          featureType: 'road.highway',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#f3d19c' }]
        },
        {
          featureType: 'transit',
          elementType: 'geometry',
          stylers: [{ color: '#2f3948' }]
        },
        {
          featureType: 'transit.station',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#d59563' }]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [{ color: '#17263c' }]
        },
        {
          featureType: 'water',
          elementType: 'labels.text.fill',
          stylers: [{ color: '#515c6d' }]
        },
        {
          featureType: 'water',
          elementType: 'labels.text.stroke',
          stylers: [{ color: '#17263c' }]
        }
      ],

      // [
      //   {elementType: 'geometry', stylers: [{color: '#ebe3cd'}]},
      //   {elementType: 'labels.text.fill', stylers: [{color: '#523735'}]},
      //   {elementType: 'labels.text.stroke', stylers: [{color: '#f5f1e6'}]},
      //   {
      //     featureType: 'administrative',
      //     elementType: 'geometry.stroke',
      //     stylers: [{color: '#c9b2a6'}]
      //   },
      //   {
      //     featureType: 'administrative.land_parcel',
      //     elementType: 'geometry.stroke',
      //     stylers: [{color: '#dcd2be'}]
      //   },
      //   {
      //     featureType: 'administrative.land_parcel',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#ae9e90'}]
      //   },
      //   {
      //     featureType: 'landscape.natural',
      //     elementType: 'geometry',
      //     stylers: [{color: '#dfd2ae'}]
      //   },
      //   {
      //     featureType: 'poi',
      //     elementType: 'geometry',
      //     stylers: [{color: '#dfd2ae'}]
      //   },
      //   {
      //     featureType: 'poi',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#93817c'}]
      //   },
      //   {
      //     featureType: 'poi.park',
      //     elementType: 'geometry.fill',
      //     stylers: [{color: '#a5b076'}]
      //   },
      //   {
      //     featureType: 'poi.park',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#447530'}]
      //   },
      //   {
      //     featureType: 'road',
      //     elementType: 'geometry',
      //     stylers: [{color: '#f5f1e6'}]
      //   },
      //   {
      //     featureType: 'road.arterial',
      //     elementType: 'geometry',
      //     stylers: [{color: '#fdfcf8'}]
      //   },
      //   {
      //     featureType: 'road.highway',
      //     elementType: 'geometry',
      //     stylers: [{color: '#f8c967'}]
      //   },
      //   {
      //     featureType: 'road.highway',
      //     elementType: 'geometry.stroke',
      //     stylers: [{color: '#e9bc62'}]
      //   },
      //   {
      //     featureType: 'road.highway.controlled_access',
      //     elementType: 'geometry',
      //     stylers: [{color: '#e98d58'}]
      //   },
      //   {
      //     featureType: 'road.highway.controlled_access',
      //     elementType: 'geometry.stroke',
      //     stylers: [{color: '#db8555'}]
      //   },
      //   {
      //     featureType: 'road.local',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#806b63'}]
      //   },
      //   {
      //     featureType: 'transit.line',
      //     elementType: 'geometry',
      //     stylers: [{color: '#dfd2ae'}]
      //   },
      //   {
      //     featureType: 'transit.line',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#dfd2ae'}]
      //   },
      //   {
      //     featureType: 'transit.line',
      //     elementType: 'labels.text.stroke',
      //     stylers: [{color: '#ebe3cd'}]
      //   },
      //   {
      //     featureType: 'transit.station',
      //     elementType: 'geometry',
      //     stylers: [{color: '#dfd2ae'}]
      //   },
      //   {
      //     featureType: 'water',
      //     elementType: 'geometry.fill',
      //     stylers: [{color: '#b9d3c2'}]
      //   },
      //   {
      //     featureType: 'water',
      //     elementType: 'labels.text.fill',
      //     stylers: [{color: '#92998d'}]
      //   }
      // ],
      { name: 'Styled Map' });

    let latLng = new google.maps.LatLng(lat, lng);
    this.map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: latLng,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      mapTypeControl: false,
      zoomControl: false,
      streetViewControl: false,
    });


    this.map.mapTypes.set('styled_map', styledMapType);
    this.map.setMapTypeId('styled_map');

    let image = 'https://png.icons8.com/cotton/2x/bad-taxi-driver.png';

    let icon = {
      url: image, // url
      scaledSize: new google.maps.Size(50, 50), // scaled size
      origin: new google.maps.Point(0, 0), // origin
      anchor: new google.maps.Point(0, 0) // anchor
    };
    new google.maps.Marker({
      map: this.map,
      icon: icon,
      animation: google.maps.Animation.DROP,
      position: latLng
    });
  }

  changeAvailability() {
    console.log(this.isDriverAvailable);
this.checkLocation();

    if (this.isAvailable && this.isDriverAvailable == true) {

      // get current location
      this.geolocation.getCurrentPosition().then((resp) => {
        let latLng = new google.maps.LatLng(resp.coords.latitude, resp.coords.longitude);
        let geocoder = new google.maps.Geocoder();

        this.loadMap(resp.coords.latitude, resp.coords.longitude);
        // find address from lat lng
        geocoder.geocode({ 'latLng': latLng }, (results, status) => {
          if (status == google.maps.GeocoderStatus.OK) {
            // save locality
            // let locality = this.placeService.setLocalityFromGeocoder(results);
            // let code = this.placeService.findCodePostFromGeocoder(results);
            console.log('locality: ', results);
            // console.log('locality', locality);
            // console.log('code', code);

            // start tracking
            this.positionTracking = setInterval(() => {
              // check for driver object, if it did not complete profile, stop updating location
              console.log("pos track....")
              // if (!this.driver || !this.driver.type) {
              //   return;
              // }
              // Immediate update
              console.log(resp.coords.latitude + " - " + resp.coords.longitude);
              //  this.driverService.updatePosition(this.driver.$key, this.driver.type, locality, resp.coords.latitude,resp.coords.longitude, this.driver.rating, this.driver.name);
              // this.driverService.updatePositionTest(this.driver.uuid, resp.coords.latitude, resp.coords.longitude,code).subscribe(data => {
              //   console.log("data update");
              //   //console.log(data);

              // }, err => {
              //   console.log("err");
              //   console.log(err);
              // });
              // Periodic update after particular time intrvel
              this.geolocation.getCurrentPosition().then((resp) => {
                console.log(resp);
                // this.driverService.updatePositionTest(this.driver.uuid, resp.coords.latitude, resp.coords.longitude,code);
                // this.driverService.updatePosition(this.driver.$key, this.driver.type, locality, resp.coords.latitude,resp.coords.longitude, this.driver.rating, this.driver.name);
              }, err => {
                console.log(err);
              });

            }, POSITION_INTERVAL);


            // this.watchDeals();
          }
        });
      }, err => {
        console.log(err);
      });

    }
    else {
      clearInterval(this.positionTracking);
      if (this.dealSubscription) {
        // unsubscribe when leave this page
        this.dealSubscription.unsubscribe();
      }
    }

  }
  ionViewWillLeave() {
    if (this.dealSubscription) {
      // unsubscribe when leave this page
      this.dealSubscription.unsubscribe();
    }
  }

  // count down
  countDown() {
    // let interval = setInterval(() => {
    //   this.remainingTime--;
    //   if (this.remainingTime == 0) {
    //     clearInterval(interval)
    //     this.cancelDeal();
    //     this.remainingTime = DEAL_TIMEOUT;
    //   }
    // }, 1000);
    // this.confirmJob();
  }

  cancelDeal() {
    console.log("close")
    this.dealStatus = false;
    // this.dealService.removeDeal(this.driver.$key);
  }

  /**
   * This method create a new status o verifid exist.
   */
  ionViewDidLoad() {
    // this.driver = JSON.parse(localStorage.getItem("currentUser"));
    // this.driver=this.driver.user;
    // console.log("ionViewDidLoad(): " + this.driver.uuid);
    // console.log("ionViewDidLoad(): " + this.driver.uuid);
    // console.log("ionViewDidLoad(): " + this.driver.profileStatus);
    // if(this.driver.profileStatus=="RGT"){
    //   // this.nav.setRoot(UserPage, {
    //   //         user: this.driver
    //   //       });
    // }else{

    // }
    // this.driverService.registerStatus(this.driver.uuid).subscribe(result => {
    //   console.log(result);
    // }, error => {
    //   this.alertCtrl.create({ message: error.message, buttons: ['OK'] }).present();
    // });
    // this.driverService.getDriver().take(1).subscribe(snapshot => {
    //   this.driver = snapshot;

    //   if (!this.driver.plate && !this.driver.type) {
    //     this.nav.setRoot(UserPage, {
    //       user: this.authService.getUserData()
    //     });
    //   }

    // });
  }

  range(n) {
    return new Array(Math.round(n));
  }

  // confirm a job
  confirmJob() {
    console.log("confirm");
    let message = "<b>From:<b>" + this.job.origin.vicinity + " - " + this.job.origin.distance + "Km <br/> <b>To:</b>" + this.job.destination.vicinity + " - " + this.job.destination.distance;

    let confirm = this.alertCtrl.create({
      title: 'New Request',
      message: message,
      buttons: [
        {
          text: 'Reject',
          handler: () => {
            console.log('Disagree clicked');
            this.dealStatus = false;
            // this.dealService.removeDeal(this.driver.$key);
          }
        },
        {
          text: 'Accept',
          handler: () => {
            this.dealStatus = false;
            // this.dealService.acceptDeal(this.driver.$key, this.deal).then(() => {
            //   // go to pickup page
            //   console.log("go to pickup page");
            //   // this.nav.setRoot(PickUpPage);
            // });
          }
        }
      ]
    });
    confirm.present();
    this.playAudio();
  }


  // listen to deals
  watchDeals() {
    // // listen to deals
    // this.dealSubscription = this.dealService.getDeal(this.driver.$key).subscribe(snapshot => {
    //   this.deal = snapshot;
    //   if (snapshot.status == DEAL_STATUS_PENDING) {
    //     // if deal expired
    //     if (snapshot.createdAt < (Date.now() - DEAL_TIMEOUT * 1000)) {
    //       //return this.dealService.removeDeal(this.driver.$key);
    //     }
    //     this.dealStatus = true;
    //     console.log(this.dealStatus);


    //     this.job = snapshot;

    //     this.geolocation.getCurrentPosition().then((resp) => {
    //       //resp.coords.longitude
    //       this.job.origin.distance = this.placeService.calcCrow(resp.coords.latitude, resp.coords.longitude, this.job.origin.location.lat, this.job.origin.location.lng).toFixed(0);
    //       this.job.destination.distance = this.placeService.calcCrow(resp.coords.latitude, resp.coords.longitude, this.job.destination.location.lat, this.job.destination.location.lng).toFixed(0);
    //       this.countDown();
    //     }, err => {
    //       console.log(err);
    //     });
    //   }
    // });
  }

  goProfile() {
    // this.nav.push(UserPage, { user: localStorage.getItem('currentUser') });
  }

  goWallet() {
    // this.nav.push(WalletPage);
  }
  goHistory() {
    // this.nav.push(JobHistoryPage);
  }
  playAudio() {
    if (PLAY_AUDIO_ON_REQUEST == true) {
      let audio = new Audio(AUDIO_PATH);
      audio.play();
    }
  }
}
