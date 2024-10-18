import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatchesComponent } from './matches/matches.component';
import { TeamsComponent } from './teams/teams.component';
import { RegistroComponent } from './registro/registro.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';

const routes: Routes = [
  { path: 'inicio-sesion', component: InicioSesionComponent }, // Componente de inicio de sesión
  { path: 'registro', component: RegistroComponent }, // Componente de registro
  { path: 'matches', component: MatchesComponent },
  { path: 'teams', component: TeamsComponent },
  // otras rutas aquí...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
