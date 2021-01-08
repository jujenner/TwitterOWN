import { Component, OnInit } from '@angular/core';
import { Feed } from '../feed';
import { ApiService } from '../api-service';

@Component({
  selector: 'app-finance-feed-anzeigen',
  templateUrl: './finance-feed-anzeigen.component.html',
  styleUrls: ['./finance-feed-anzeigen.component.css'],
  providers: [ApiService]
})
export class FinanceFeedAnzeigenComponent implements OnInit {

public displayedColumns: string[] = ['id', 'keyword', 'erstelltAm', 'suchintervall','status', 'dauer'];

 public dataSource: Feed[] = [];

 constructor(private apiService: ApiService) { }

 ngOnInit(): void {
   this.apiService.getFeeds().subscribe(feeds => {
      this.dataSource = feeds
   });
 }

}
