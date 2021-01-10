import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MaterialModule} from './material-module';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HauptmenueComponent } from './hauptmenue/hauptmenue.component';
import { FinanceFeedAnzeigenComponent } from "./finance-feed-anzeigen/finance-feed-anzeigen.component";
import { FinanceFeedAnlegenComponent } from "./finance-feed-anlegen/finance-feed-anlegen.component";
import { AnalyseErgebnisComponent } from "./analyse-ergebnis/analyse-ergebnis.component";

import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    HauptmenueComponent,
    FinanceFeedAnzeigenComponent,
    FinanceFeedAnlegenComponent,
    AnalyseErgebnisComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ChartsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
