import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api-service';
import { Feed } from '../feed';

@Component({
  selector: 'app-hauptmenue',
  templateUrl: './hauptmenue.component.html',
  styleUrls: ['./hauptmenue.component.css'],
  providers: [ApiService]
})
export class HauptmenueComponent implements OnInit {

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
  }

  getFeeds(): void {
    console.log("Start requesting feeds");
    this.apiService.getFeeds().subscribe(feeds => {
        console.log("New feed "+feeds);
    });

  }

}
