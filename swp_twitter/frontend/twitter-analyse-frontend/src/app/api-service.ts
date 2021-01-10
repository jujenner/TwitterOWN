import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Feed } from './feed';
import { FeedDto } from './feedDto';
import { Observable } from 'rxjs';
import { map, filter, switchMap } from 'rxjs/operators';
import { TwitterStatusDto } from './twitter-status-dto';
import { TwitterStatus } from './twitter-status';
import { AnalyseDto } from './analyse-ergebnis/analyse-dto';
import { Analyse, SentimentType } from './analyse-ergebnis/analyse';

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
      feeds.map(feed => this.mapFeed(feed))
    )
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
      .pipe(map((feed: FeedDto) => this.mapFeed(feed)))
  }

  private mapFeed(feedDto:FeedDto): Feed {
    return new Feed(feedDto.id, feedDto.keyword, new Date(feedDto.erstelltAm), new Date(feedDto.suchIntervall), "",new Date(feedDto.suchDauer), 
    feedDto.twitterStatus.map((status: TwitterStatusDto) => new TwitterStatus(status.id, status.tweetId, status.nutzerNamen, new Date(status.erstelltAm), this.mapErgebnis(status.ergebnis))));
  }

  private mapErgebnis(analyse: AnalyseDto): Analyse {
    var type = SentimentType.NEUTRAL
     switch(analyse.sentimentTyp){
        case 0: 
          type = SentimentType.SEHR_POSITIV;
          break;
        case 1: 
          type = SentimentType.POSITIV
         break;
        case 3: 
          type = SentimentType.NEGATIV
          break;
        case 4: 
          type = SentimentType.SEHR_NEGATIV
          break;
        default: 
          type = SentimentType.NEUTRAL;
      }
      return new Analyse(analyse.id, type)
  }
}
