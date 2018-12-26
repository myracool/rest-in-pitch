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
  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
     this.getSearchShow("mary");
  }
  // onSearch(term:string) {
  //   this.router.navigate(['http://localhost:8080/rest-in-pitch/rest/search', {term: term});
  // }

  getSearchShow(queryparam) {
    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',{
      params: {'name' : queryparam}
      }).subscribe(data => {
        this.shows = data;
      });
  }



}
