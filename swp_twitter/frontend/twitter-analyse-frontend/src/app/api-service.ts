import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Feed } from './feed';
import { Observable } from 'rxjs';

@Injectable()
export class ApiService {
  constructor(private http: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'content-type' : 'application/json'
    })
  };


  /**
   * getFeeds
   */
  public getFeeds() : Observable<Feed[]> {
    return this.http.get<Feed[]>("http://localhost:8080/feeds", this.httpOptions)
  }
}
