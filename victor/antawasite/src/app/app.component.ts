import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  //static API_URL="http://192.168.8.102:8080";
 static API_URL="http://localhost:8080/plataforma";
 //static API_URL="http://192.168.1.18:8080";
}
