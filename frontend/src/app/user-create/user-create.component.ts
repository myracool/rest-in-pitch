import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  user = {};

  constructor(private http: HttpClient, private router: Router, private location: Location) { }

  ngOnInit() {
  }

  saveContact() {
    this.http.post('http://localhost:8080/rest-in-pitch/rest/user', this.user)
      .subscribe(res => {
          this.router.navigate(['/show']);
        }, (err) => {
          console.log(err);
        }
      );
  }

  backClicked() {
    this.location.back();
  }

}
