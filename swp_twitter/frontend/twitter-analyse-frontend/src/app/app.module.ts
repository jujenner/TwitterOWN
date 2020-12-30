import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MaterialModule} from './material-module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FinanceFeedAnlegenComponent } from './finance-feed-anlegen/finance-feed-anlegen.component';
import { FinanceFeedsAnzeigenComponent } from './finance-feeds-anzeigen/finance-feeds-anzeigen.component';
import { AnalyseErgebnisComponent } from './analyse-ergebnis/analyse-ergebnis.component';

@NgModule({
  declarations: [
    AppComponent,
    FinanceFeedAnlegenComponent,
    FinanceFeedsAnzeigenComponent,
    AnalyseErgebnisComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
