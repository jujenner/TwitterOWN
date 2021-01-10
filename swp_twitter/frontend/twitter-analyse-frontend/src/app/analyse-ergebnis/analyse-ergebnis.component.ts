import { Component, OnInit, Inject } from '@angular/core';
import { Feed } from '../feed';
import { ApiService } from '../api-service';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { TwitterStatus } from '../twitter-status';
import { SentimentType } from './analyse';

@Component({
  selector: 'app-analyse-ergebnis',
  templateUrl: './analyse-ergebnis.component.html',
  styleUrls: ['./analyse-ergebnis.component.css'],
  providers: [ApiService]
})
export class AnalyseErgebnisComponent implements OnInit {
  feed: Feed | null = null;

  constructor(private apiService: ApiService) { }


 // Pie
 public pieChartLabels:string[] = ['Sehr Positiv', 'Positiv', 'Neutral','Negativ','Sehr Negativ'];
 public pieChartData:number[] = [0,0,0,0,0];
 private pieColors=[
  {
    backgroundColor: [
      'rgba(8, 127, 35, 1)',
      'rgba(76, 175, 80, 1)',
      'rgba(236, 239, 241, 1)',
      'rgba(183, 28, 28, 1)',
      'rgba(127, 0, 0, 1)'
  ]
  }
];

public keyword = ""
public letzteAkutalisierung = ""

 // events
 public chartClicked(e:any):void {
   console.log(e);
 }

 public chartHovered(e:any):void {
   console.log(e);
 }

  ngOnInit(): void {
    const { feedId } = window.history.state
    this.apiService.getFeed(feedId).subscribe(feed => {
      this.feed = feed
      this.keyword = feed.keyword
      var latestErgebnis= this.feed?.twitterStatus.sort((a: TwitterStatus, b: TwitterStatus) => {
          return a.erstelltAm.getTime() - b.erstelltAm.getTime()
      }) 
      console.log(latestErgebnis);
      

      if(latestErgebnis?.length != 0 && latestErgebnis != null){
        this.letzteAkutalisierung = latestErgebnis[0].erstelltAm.toLocaleString()
      }
  
       var positivCount = 0
       var sehrPositivCount = 0
       var neutralCount = 0
       var negativeCount = 0
       var sehrNegativCount = 0
       this.feed.twitterStatus.forEach((status: TwitterStatus) => {
         switch(status.ergebnis.sentimentType){
          case SentimentType.SEHR_POSITIV:
                sehrPositivCount++
                break;
          case SentimentType.POSITIV:
                positivCount++
                break;
          case SentimentType.NEUTRAL:
                neutralCount++
                break;
          case SentimentType.NEGATIV:
                  negativeCount++
                  break;
          case SentimentType.SEHR_NEGATIV:
                  sehrNegativCount++
                  break;
         }
       })

       this.pieChartData = [sehrPositivCount, positivCount, neutralCount, negativeCount, sehrNegativCount]

    });
  }

}
