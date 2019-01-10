import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {
  shows: any;
  res: string;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('http://localhost:8080/rest-in-pitch/rest/rand/12').subscribe(data => {
    this.shows = data;
  });
  }

  getNextPage(page) {
     this.http.get('http://localhost:8080/rest-in-pitch/rest/shows/page/'+page).subscribe( data => {
       this.shows = data;
     });
   }

  search() :void {
     this.res = (<HTMLInputElement>document.getElementById("search")).value;
    let checkboxes = (<HTMLInputElement[]><any>document.getElementsByClassName('custom-control-input'));
    let values : string[] = [];

    for (let i = 0 ; i < checkboxes.length; i++) {
      if (checkboxes[i].checked) {
        values.push(checkboxes[i].value);
      }
    }

    let params;
    if(values.length != 0) {
      for (let i = 0; i < values.length; i++) {
        params = new HttpParams().set("name",this.res).set("genre",values[i]);
      }
    }
    else params = new HttpParams().set("name",this.res);

    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',
    {params: params}).subscribe(data => {
        this.shows = data;
      });
  }
}

// let params = new HttpParams().set("paramName",paramValue).set("paramName2", paramValue2); //Create new HttpParams
// this.http.get(url, {headers: headers, params: params});
