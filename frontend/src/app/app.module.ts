import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowComponent } from './show/show.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { ShowDetailComponent } from './show-detail/show-detail.component';
import { ShowSearchComponent } from './show-search/show-search.component';

const appRoutes: Routes = [
  {
    path: 'show',
    component: ShowComponent,
    data: { title: 'Show List' }
  },
  {
    path: 'show-detail/:id',
    component: ShowDetailComponent,
    data: { title: 'Show Details' }
  },
  {
    path: 'show-search',
    component: ShowSearchComponent,
    data: { title: 'Show Search' }
  },
  { path: '',
    redirectTo: '/show',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ShowComponent,
    ShowDetailComponent,
    ShowSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
   appRoutes,
   { enableTracing: true } // <-- debugging purposes only
 )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
