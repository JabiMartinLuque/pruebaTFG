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

  constructor(private router: Router, private matchService: MatchService) { }

  ngOnInit(): void {
    this.matchService.getMatches().subscribe(matches => this.matches = matches);
  }

  goToMatchDetail(id: string): void {
    this.router.navigate(['/matches', id]);
  }
}