import { Component, OnInit } from '@angular/core';
import { Feed } from '../feed';
import { Router } from '@angular/router';
import { ApiService } from '../api-service';

@Component({
  selector: 'app-analyse-ergebnis',
  templateUrl: './analyse-ergebnis.component.html',
  styleUrls: ['./analyse-ergebnis.component.css'],
  providers: [ApiService]
})
export class AnalyseErgebnisComponent implements OnInit {
  feed: Feed | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    const { feedId } = window.history.state
    this.apiService.getFeed(feedId).subscribe(feed => {
       this.feed = feed
       console.log(feed);

    });
  }

}
