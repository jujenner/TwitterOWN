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
  public pieColors=[
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

public lineChartData: ChartDataSets[] = [];
public lineChartLabels: Label[] = [];
public lineChartColors: Color[] = [

];
public lineChartLegend = true;
public lineChartType = 'line';

public keyword = ""
public letzteAkutalisierung = ""


  public refresh() {
    console.log("Refresh the data");
    
    if(this.feed == null) return
    this.loadData(this.feed.id)
  }

  ngOnInit(): void {
    const { feedId } = window.history.state
    this.loadData(feedId)
  }

  /**
   * loadData
   
feedId: number   */

  public loadData(feedId: number) {
    this.apiService.getFeed(feedId).subscribe(feed => {
      this.feed = feed
      this.keyword = feed.keyword
      var latestErgebnis= this.feed?.twitterStatus.sort((a: TwitterStatus, b: TwitterStatus) => {
          return b.erstelltAm.getTime() - a.erstelltAm.getTime()
      }) 
      

      if(latestErgebnis?.length != 0 && latestErgebnis != null){
        this.letzteAkutalisierung = latestErgebnis[0].erstelltAm.toLocaleString()
      }
  
       var positivCount = 0
       var sehrPositivCount = 0
       var neutralCount = 0
       var negativeCount = 0
       var sehrNegativCount = 0
       var positivData: number[] = []
       var sehrPositvData: number[] = []
       var neutralData: number[] = []
       var negativData: number[] = []
       var sehrNegativData: number[] = []
       this.lineChartLabels = []
       this.feed.twitterStatus.forEach((status: TwitterStatus) => {
        this.lineChartLabels.push(status.erstelltAm.toLocaleString())
         switch(status.ergebnis.sentimentType){
          case SentimentType.SEHR_POSITIV:
            sehrPositivCount = sehrPositivCount +1
                break;
          case SentimentType.POSITIV:
            positivCount = positivCount +1
                break;
          case SentimentType.NEUTRAL:
            neutralCount = neutralCount +1
                break;
          case SentimentType.NEGATIV:
            negativeCount = negativeCount +1
                  break;
          case SentimentType.SEHR_NEGATIV:
            sehrNegativCount = sehrNegativCount +1
                  break;
         }

         positivData.push(positivCount / (positivCount + sehrPositivCount + neutralCount + negativeCount +sehrNegativCount))
         sehrPositvData.push(sehrPositivCount / (positivCount + sehrPositivCount + neutralCount + negativeCount +sehrNegativCount))
         neutralData.push(neutralCount / (positivCount + sehrPositivCount + neutralCount + negativeCount +sehrNegativCount))
         negativData.push(negativeCount / (positivCount + sehrPositivCount + neutralCount + negativeCount +sehrNegativCount))
         sehrNegativData.push(sehrNegativCount / (positivCount + sehrPositivCount + neutralCount + negativeCount +sehrNegativCount))
       })

       this.lineChartData = []
      this.lineChartData  = [
        { data: sehrPositvData, label: 'Sehr Postiv', backgroundColor: 'rgba(8, 127, 35, 0)', borderColor: 'rgba(8, 127, 35, 1)',  pointBackgroundColor:  'rgba(8, 127, 35, 1)'},
        { data: positivData, label: 'Positiv', backgroundColor: 'rgba(76, 175, 80, 0)', borderColor: 'rgba(76, 175, 80, 1)', pointBackgroundColor: 'rgba(76, 175, 80, 1)' },
        { data: neutralData, label: 'Neutral', backgroundColor: 'rgba(236, 239, 241, 0)', borderColor: 'rgba(236, 239, 241, 1)', pointBackgroundColor: 'rgba(236, 239, 241, 1)'  },
        { data: negativData, label: 'Negativ', backgroundColor:  'rgba(183, 28, 28, 0)',  borderColor:  'rgba(183, 28, 28, 1)', pointBackgroundColor: 'rgba(183, 28, 28, 1)' },
        { data: sehrNegativData, label: 'Sehr Negativ', backgroundColor: 'rgba(127, 0, 0, 0)', borderColor: 'rgba(127, 0, 0, 1)', pointBackgroundColor: 'rgba(127, 0, 0, 1)' },
      ]

      this.pieChartData = []
       this.pieChartData = [sehrPositivCount, positivCount, neutralCount, negativeCount, sehrNegativCount]

    });
    
  }

}
