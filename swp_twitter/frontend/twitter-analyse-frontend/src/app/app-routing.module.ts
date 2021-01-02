import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FinanceFeedAnlegenComponent} from './finance-feed-anlegen/finance-feed-anlegen.component'
import { FinanceFeedAnzeigenComponent} from './finance-feed-anzeigen/finance-feed-anzeigen.component'
import { HauptmenueComponent} from './hauptmenue/hauptmenue.component'
import { AnalyseErgebnisComponent } from './analyse-ergebnis/analyse-ergebnis.component'

const routes: Routes = [
  { path:'', redirectTo:'hauptmenue',  pathMatch: 'full' },
  { path: 'hauptmenue', component: HauptmenueComponent },
  { path: 'finance-feed-anlegen', component: FinanceFeedAnlegenComponent },
  { path: 'finance-feed-anzeigen', component: FinanceFeedAnzeigenComponent },
  { path: 'analyse-ergebnis', component: AnalyseErgebnisComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
