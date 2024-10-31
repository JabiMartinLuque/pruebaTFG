import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatchesComponent } from './matches/matches.component';
import { TeamsComponent } from './teams/teams.component';
import { RegistroComponent } from './registro/registro.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { PrincipalComponent } from './principal/principal.component';
import { MatchDetailComponent } from './match-detail/match-detail.component';
import { NoticiasComponent } from './noticias/noticias.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: 'inicio-sesion', component: InicioSesionComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'matches', component: MatchesComponent, canActivate: [AuthGuard] },
  { path: 'principal', component: PrincipalComponent, canActivate: [AuthGuard] },
  { path: 'teams', component: TeamsComponent, canActivate: [AuthGuard] },
  { path: 'matches/:id', component: MatchDetailComponent, canActivate: [AuthGuard] }, 
  { path: 'noticias', component: NoticiasComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/inicio-sesion', pathMatch: 'full' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
