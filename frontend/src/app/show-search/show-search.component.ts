import { ViewChild, Component, OnInit } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { ParamMap, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-search',
  templateUrl: './show-search.component.html',
  styleUrls: ['./show-search.component.css']
})

export class ShowSearchComponent implements OnInit {
  shows: any;
  //search= "mary";
  //@ViewChild('res') res;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    // let str : string = (<HTMLInputElement>document.getElementById("search")).value;
    this.http.get('http://localhost:8080/rest-in-pitch/rest/search',
    {
      params: {'name' : this.res._element.nativeElement}
      }).subscribe(data => {
        this.shows = data;
      });
  }


}
