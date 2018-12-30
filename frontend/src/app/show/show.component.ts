import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
    this.http.get('http://localhost:8080/rest-in-pitch/rest/rand/10').subscribe(data => {
    this.shows = data;
  });
  }

  search(): void {
  let genre :string = (<HTMLInputElement>document.getElementById("Comedy")).value;
    let res : string = (<HTMLInputElement>document.getElementById("search")).value;

    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',
    {
      params: {'name' : res}
               // }'genre' :genre
      }).subscribe(data => {
        this.shows = data;
      });
  }
}
