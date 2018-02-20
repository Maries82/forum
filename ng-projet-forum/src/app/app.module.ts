import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { UserListViewComponent } from './user-list-view/user-list-view.component';
import {FormsModule} from "@angular/forms";
import {DataService} from "./data-service.service";
import {HttpClientModule} from "@angular/common/http";
import { TopicViewComponent } from './topic-view/topic-view.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListViewComponent,
    TopicViewComponent
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
