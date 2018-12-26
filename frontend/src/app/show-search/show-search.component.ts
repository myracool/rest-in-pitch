import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { ParamMap, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-search',
  templateUrl: './show-search.component.html',
  styleUrls: ['./show-search.component.css']
})

export class ShowSearchComponent implements OnInit {
  shows: any;
  search= "mary";

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    //var res = ((document.getElementById("num1") as HTMLInputElement).value);
    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',
    {
      params: {'name' : this.search}
      }).subscribe(data => {
        this.shows = data;
      });
  }


}
