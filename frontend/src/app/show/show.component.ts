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

  ngOnInit() {
    this.http.get('/shows').subscribe(data => {
    this.shows = data;
  });
  }

}
