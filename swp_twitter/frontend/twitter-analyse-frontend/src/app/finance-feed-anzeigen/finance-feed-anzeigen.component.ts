import { Component, OnInit } from '@angular/core';
import { Feed } from '../feed';

@Component({
  selector: 'app-finance-feed-anzeigen',
  templateUrl: './finance-feed-anzeigen.component.html',
  styleUrls: ['./finance-feed-anzeigen.component.css']
})
export class FinanceFeedAnzeigenComponent implements OnInit {

public displayedColumns: string[] = ['id', 'keyword', 'erstelltAm', 'suchintervall','status', 'dauer'];

feeds = [
  new Feed(1, "SAP", new Date(), new Date(), "Positiv", new Date()),
  new Feed(1, "IBM", new Date(), new Date(), "Negativ", new Date()),
];

 public dataSource = this.feeds;

 constructor() { }

 ngOnInit(): void {
 }

}
