import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../model/user";
import {Topic} from "../model/topic";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {


  users: User[];
  //selectedUser: User;
  selectedTopic: Topic;
  theUser: User; // Utilisateur sélectionné dans la liste

  createdTopic:Topic = new Topic();
  createdUser: User = new User();

  constructor(public dataService:DataService) {
    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users:',users));
  }

  ngOnInit() {
  }

  details (){
    //this.selectedUser = user;
    /*this.createdTopic.user = user;
    this.createdTopic.name = user.name + "'s topic"*/

    console.log('You selected ' + this.indexedUser);
    this.dataService
      .fetchUsersWithTopics(this.users[this.indexedUser])
      .then(user => this.dataService.selectedUser = user)
      .then(console.log);
  }


  createUser(){
    this.dataService.createUser(this.createdUser)
      .then (() => this.users.push(Object.assign({},this.createdUser)))
      .catch(e=> alert(e))
  }
}
