import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParamMap, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-detail',
  templateUrl: './show-detail.component.html',
  styleUrls: ['./show-detail.component.css']
})

export class ShowDetailComponent implements OnInit {

  show = {};

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.getShowDetail(this.route.snapshot.params['id']);
  }

  getShowDetail(id) {
   this.http.get('http://localhost:8080/rest-in-pitch/rest/show/'+id).subscribe( data => {
     this.show = data;
   });
 }

}
