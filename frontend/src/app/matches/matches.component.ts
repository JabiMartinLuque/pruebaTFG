import { Component, OnInit } from '@angular/core';
import { MatchService } from '../services/match.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  matches: any[] = [];

  constructor(private matchService: MatchService) { }

  ngOnInit(): void {
    this.matchService.getMatches().subscribe(
      (data: any) => {
        console.log('API Response:', data);  // Verifica la respuesta en la consola
        this.matches = data.matches;  // Ajusta según el formato de respuesta
      },
      (error: any) => {
        console.error('Error fetching matches:', error);  // Maneja y muestra errores
      }
    );
  }
}