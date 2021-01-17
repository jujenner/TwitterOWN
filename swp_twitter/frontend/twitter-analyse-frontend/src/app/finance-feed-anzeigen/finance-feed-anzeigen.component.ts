import { Component, OnInit } from '@angular/core';
import { Feed } from '../feed';
import { ApiService } from '../api-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-finance-feed-anzeigen',
  templateUrl: './finance-feed-anzeigen.component.html',
  styleUrls: ['./finance-feed-anzeigen.component.css'],
  providers: [ApiService]
})
export class FinanceFeedAnzeigenComponent implements OnInit {

public displayedColumns: string[] = ['keyword', 'erstelltAm', 'suchintervall', 'dauer'];

 public dataSource: Feed[] = [];

 constructor(private apiService: ApiService, private router: Router) { }

 ngOnInit(): void {
   this.apiService.getFeeds().subscribe(feeds => {
      this.dataSource = feeds
   });
 }

 onItemClicked(element: Feed){
   console.log(element);
   this.router.navigate(['/analyse-ergebnis'], {state: {feedId: element.id}})
 }

}
