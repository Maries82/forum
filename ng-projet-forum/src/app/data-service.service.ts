import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Topic} from "./model/topic";
import {Comment} from "./model/comment";


@Injectable()

export class DataService {

  constructor(public http:HttpClient) { }

  fetchUsers():Promise<User[]>{

    return this.http
      .get('http://localhost:8080/forum/api/users')
      .toPromise()
      .then(data => data as User[])
  }

  fetchUsersWithTopics(user: User): Promise<User>{

    let url = 'http://localhost:8080/forum/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with topic : ', data);
        return data as User
      })
  }

  createTopic(topic: Topic){
    let url = 'http://localhost:8080/forum/api/topics';

    let dto = {
      name: topic.name,
      user: topic.user,
     // comments: user.comments,
    };

    return this.http.post(url, dto)
      .toPromise()
      .then(data=> console.log('Success', data))

  }

  createComment (comment: Comment){
    let url = 'http://localhost:8080/forum/api/comments'
    let dto = {
      user : comment.user,
      topic : comment.topic
    }

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success',data));

  }

  createUser (user: User){
    let url = 'http://localhost:8080/forum/api/users'

    let dto = {
      name: user.name,
    }

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success',data));

  }

}
