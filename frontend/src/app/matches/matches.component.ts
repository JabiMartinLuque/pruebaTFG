import { Component, OnInit } from '@angular/core';
import { MatchService } from '../services/match.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  matches: any[] = [];
  matchesESP: any[] = [];
  matchesENG: any[] = [];

  constructor(private router: Router, private matchService: MatchService) { }

  ngOnInit(): void {
    this.matchService.getMatches().subscribe(
      matches => {
        this.matches = matches;
        console.log('Matches:', this.matches);
      },
      error => {
        console.error('Error al obtener los matches:', error);
      }
    );
  
    this.matchService.getMatchesEsp().subscribe(
      matchesESP => {
        this.matchesESP = matchesESP;
        console.log('Eventos:', this.matchesESP);
      },
      error => {
        console.error('Error al obtener los eventos:', error);
      }
    );

    this.matchService.getMatchesEng().subscribe(
      matchesENG => {
        this.matchesENG = matchesENG;
        console.log('Events:', this.matchesENG);
      },
      error => {
        console.error('Error getting events:', error);
      }
    );
  }
  goToMatchDetail(id: string): void {
    this.router.navigate(['/matches', id]);
  }
}