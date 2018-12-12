import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowComponent } from './show/show.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { ShowDetailComponent } from './show-detail/show-detail.component';

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
  { path: '',
    redirectTo: '/show',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ShowComponent,
    ShowDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
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
