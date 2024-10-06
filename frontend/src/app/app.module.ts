import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'; // Importa RouterModule y Routes
import { HttpClientModule } from '@angular/common/http';  // Importa HttpClientModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatchesComponent } from './matches/matches.component';

// Define las rutas directamente en app.module.ts
const routes: Routes = [
  { path: 'matches', component: MatchesComponent },
  // otras rutas aquí...
];

@NgModule({
  declarations: [
    AppComponent,
    MatchesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes) // Configura RouterModule con las rutas definidas
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }