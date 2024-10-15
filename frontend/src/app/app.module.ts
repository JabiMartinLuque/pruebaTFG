import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'; 
import { HttpClientModule } from '@angular/common/http';  

import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatchesComponent } from './matches/matches.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { TeamsComponent } from './teams/teams.component';

// Define las rutas directamente en app.module.ts
const routes: Routes = [
  { path: 'matches', component: MatchesComponent },
  // otras rutas aquí...
];

@NgModule({
  declarations: [
    AppComponent,
    MatchesComponent,
    TeamsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    RouterModule.forRoot(routes) // Configura RouterModule con las rutas definidas
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }