import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { Feed } from '../feed';
import { ApiService } from '../api-service';



@Component({
  selector: 'app-finance-feed-anlegen',
  templateUrl: './finance-feed-anlegen.component.html',
  styleUrls: ['./finance-feed-anlegen.component.css'],
  providers: [ApiService]
})
export class FinanceFeedAnlegenComponent implements OnInit {

    selectedIntervallValue = "";
    selectedSearchDauer: Date = new Date();
    suchwort = ""

    constructor(private apiService: ApiService) { }

     ngOnInit(): void {
      }

      setSearchDauer(type: string, event: MatDatepickerInputEvent<Date>) {
        if (event.value != null) {
          this.selectedSearchDauer = event.value;
        }
      }

       submit(): void {
        var suchIntervall = new Date()
        suchIntervall.setHours(0)
        suchIntervall.setMinutes(0);
        suchIntervall.setSeconds(0)
        switch (this.selectedIntervallValue) {
          case "2min":
            suchIntervall.setMinutes(2);
            break;
            case "1h":
            suchIntervall.setHours(1);
            break;
            case "2h":
            suchIntervall.setHours(2);
            break;
          default:
            break;
        }
        var feed = new Feed(1, this.suchwort, new Date(),suchIntervall, "Positiv",  this.selectedSearchDauer);
        this.apiService.createFeed(feed).subscribe(feed => {
          console.log("Created new feed "+feed);
        })
      }

//feeds = [
//  new Feed(1, "SAP", new Date(), new Date(), "Positiv", new Date()),
//  new Feed(1, "IBM", new Date(), new Date(), "Negativ", new Date()),
//];

// public dataSource = this.feeds;
   }







//}
