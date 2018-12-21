import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-show-search',
  templateUrl: './show-search.component.html',
  styleUrls: ['./show-search.component.css']
})
export class ShowSearchComponent implements OnInit {

  name = string;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
  //  this.getContactDetail(this.route.snapshot.params['id']);
  /*  this.name = this.route.snapshot.queryParamMap.get("paramName")*/

  /*  this.route.queryParamMap.subscribe(queryParams => {
      this.name = queryParams.get("paramName")
    })
    this.name = this.route.snapshot.paramMap.get('name');
*/
  }

  search(terms: Observable<string>) {
    return terms.debounceTime(400)
      .distinctUntilChanged()
      .switchMap(term => this.searchEntries(term));
  }

  getSearchShow(term) {
    this.http.get("http://localhost:8080/rest-in-pitch/rest/search?" + term)
        .map(res => res.json()).subscribe(data => {
      this.contact = data;
    });
  }

}
