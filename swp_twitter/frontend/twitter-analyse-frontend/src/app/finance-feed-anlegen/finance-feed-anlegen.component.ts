import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { Feed } from '../feed';
import { ApiService } from '../api-service';
import {MatDialog} from '@angular/material/dialog';



@Component({
  selector: 'app-finance-feed-anlegen',
  templateUrl: './finance-feed-anlegen.component.html',
  styleUrls: ['./finance-feed-anlegen.component.css'],
  providers: [ApiService]
})
export class FinanceFeedAnlegenComponent implements OnInit {

    selectedIntervallValue = "";
    public now = new Date()
    selectedSearchDauer: Date = this.now;
    suchwort = ""

    constructor(private apiService: ApiService, public dialog: MatDialog) { }

     ngOnInit(): void {
      }

      setSearchDauer(type: string, event: MatDatepickerInputEvent<Date>) {
        if (event.value != null) {
          this.selectedSearchDauer = event.value;
        }
      }

      openErfolgDialog() {
        this.dialog.open(FinanceFeedAnlegeErfolgDialog);
      }

      openFehlerDialog() {
        this.dialog.open(FinanceFeedAnlegenFehlerDialog);
      }

       submit(): void {

        if(this.selectedIntervallValue == "" || this.suchwort == "" || this.selectedSearchDauer == this.now){
          this.openFehlerDialog()
          return
        }

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
        var feed = new Feed(1, this.suchwort, new Date(),suchIntervall, "Positiv",  this.selectedSearchDauer, []);
        this.apiService.createFeed(feed).subscribe(feed => {
          console.log("Created new feed "+feed);
          this.openErfolgDialog()
        })
      }
   }


@Component({
  selector: 'finance-feed-anlege-erfolg-dialog',
  templateUrl: 'finance-feed-anlege-erfolg-dialog.html',
})
export class FinanceFeedAnlegeErfolgDialog {}

@Component({
  selector: 'finance-feed-anlegen-fehler-dialog',
  templateUrl: 'finance-feed-anlegen-fehler-dialog.html',
})
export class FinanceFeedAnlegenFehlerDialog {}
