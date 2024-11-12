import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatchesComponent } from './features/match/matches/matches.component';
import { TeamsComponent } from './features/team/teams/teams.component';
import { RegistroComponent } from './features/gestion-usuarios/registro/registro.component';
import { InicioSesionComponent } from './features/gestion-usuarios/inicio-sesion/inicio-sesion.component';
import { PrincipalComponent } from './principal/principal.component';
import { MatchDetailComponent } from './features/match/match-detail/match-detail.component';
import { NoticiasComponent } from './features/noticias/noticias.component';
import { StandingComponent } from './features/standing/standing.component';
import { EquipoComponent } from './features/team/equipo/equipo.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: 'inicio-sesion', component: InicioSesionComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'matches', component: MatchesComponent, canActivate: [AuthGuard] },
  { path: 'principal', component: PrincipalComponent, canActivate: [AuthGuard] },
  { path: 'teams', component: TeamsComponent, canActivate: [AuthGuard] },
  { path: 'matches/:id', component: MatchDetailComponent, canActivate: [AuthGuard] }, 
  { path: 'noticias', component: NoticiasComponent, canActivate: [AuthGuard] },
  { path: 'standing', component: StandingComponent, canActivate: [AuthGuard] },
  { path : 'equipo/:id', component: EquipoComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/inicio-sesion', pathMatch: 'full' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
