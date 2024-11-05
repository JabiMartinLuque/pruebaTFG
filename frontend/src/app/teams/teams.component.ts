import { Component, OnInit } from '@angular/core';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.css'
})
export class TeamsComponent implements OnInit {

  teams: any[] = [];
  http: any;

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.teamService.getTeams().subscribe(
      (data: any) => {
        console.log('API Response:', data);  // Verifica la respuesta en la consola
        this.teams = data.teams;  // Ajusta según el formato de respuesta
      },
      (error: any) => {
        console.error('Error fetching teams:', error);  // Maneja y muestra errores
      }
    );
  }


}
