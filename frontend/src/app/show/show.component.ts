import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {
  shows: any;
  constructor(private http: HttpClient) { }
//  genre ="";

  ngOnInit() {
    this.http.get('http://localhost:8080/rest-in-pitch/rest/rand/12').subscribe(data => {
    this.shows = data;
  });
  }

  search(): void {
    // let genre ;
    // let ComedyCheck = (<HTMLInputElement>document.getElementById("customCheck1"));

    let res : string = (<HTMLInputElement>document.getElementById("search")).value;
    // let checkboxes = document.querySelectorAll('input[name="checkgenre"]:checked') as HTMLElement;
    let checkboxes = (<HTMLInputElement[]><any>document.getElementsByClassName('custom-control-input'));
    let values = [];

    for (let i = 0 ; i < checkboxes.length; i++) {
      if (checkboxes[i].checked) {
        values.push(checkboxes[i].value);
      }
    }

    console.log(values.length);
    // if(genreCheck.checked == true) genre = "Comedy";
  //  else genre = "";

    let params;

    for (let i = 0; i < values.length; i++){
      params = new HttpParams().set("name",res).set("genre",values[i]);
    //  params.set("genre",values[i]);
    }

    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',
    {params: params}).subscribe(data => {
        this.shows = data;
      });
  }
}

// let params = new HttpParams().set("paramName",paramValue).set("paramName2", paramValue2); //Create new HttpParams
// this.http.get(url, {headers: headers, params: params});
