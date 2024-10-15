import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatchesComponent } from './matches/matches.component';
import { TeamsComponent } from './teams/teams.component';

const routes: Routes = [
  { path: 'matches', component: MatchesComponent },
  { path: 'teams', component: TeamsComponent },
  // otras rutas aquí...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
