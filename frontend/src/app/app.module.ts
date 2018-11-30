import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowComponent } from './show/show.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {
    path: 'show',
    component: ShowComponent,
    data: { title: 'Show List' }
  },
  { path: '',
    redirectTo: '/show',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ShowComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
    RouterModule.forRoot(
   appRoutes,
   { enableTracing: true } // <-- debugging purposes only
 )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
