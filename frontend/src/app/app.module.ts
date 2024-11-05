import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'; 
import { HttpClientModule } from '@angular/common/http';  
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatchesComponent } from './matches/matches.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { TeamsComponent } from './teams/teams.component';
import { RegistroComponent } from './registro/registro.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { PrincipalComponent } from './principal/principal.component';
import { MatchDetailComponent } from './match-detail/match-detail.component';
import { NoticiasComponent } from './noticias/noticias.component';
import { StandingComponent } from './standing/standing.component';

// Define las rutas directamente en app.module.ts
const routes: Routes = [
  { path: 'matches', component: MatchesComponent },
  // otras rutas aquí...
];

@NgModule({
  declarations: [
    AppComponent,
    MatchesComponent,
    TeamsComponent,
    RegistroComponent,
    InicioSesionComponent,
    PrincipalComponent,
    MatchDetailComponent,
    NoticiasComponent,
    StandingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
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