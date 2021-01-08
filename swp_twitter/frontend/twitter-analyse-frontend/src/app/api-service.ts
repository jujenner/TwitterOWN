import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Feed } from './feed';
import { FeedDto } from './feedDto';
import { Observable } from 'rxjs';
import { map, filter, switchMap } from 'rxjs/operators';

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
    return this.http.get<FeedDto[]>("http://localhost:8080/feeds", this.httpOptions)
    .pipe(
      map((feeds: FeedDto[]) =>
      feeds.map(feed => new Feed(feed.id, feed.keyword, new Date(feed.erstelltAm), new Date(feed.suchIntervall), "",new Date(feed.suchDauer))
    ))
    )
  }

  /**
   * createFeed
feed: Feed   */
  public createFeed(feed: Feed): Observable<void> {
    var url = "http://localhost:8080/feed/"+feed.keyword+"/"+20+"/"+feed.suchintervall.getTime()
    if (feed.dauer != null) {
      url = url+"/"+feed.dauer.getTime()

    }

  return  this.http.post<void>(url,this.httpOptions)

  }

  /**
   * getFeed
id: number : Observable<Feed>  */
  public getFeed(id: number): Observable<Feed> {
      return this.http.get<FeedDto>("http://localhost:8080/feed/"+id, this.httpOptions)
      .pipe(map((feed: FeedDto) => new Feed(feed.id, feed.keyword, new Date(feed.erstelltAm), new Date(feed.suchIntervall), "",new Date(feed.suchDauer))))
  }
}
