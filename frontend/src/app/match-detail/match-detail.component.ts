import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchService } from '../services/match.service'; // Asegúrate de tener un servicio para obtener los datos del partido

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.css']
})
export class MatchDetailComponent implements OnInit {
  match: any;

  constructor(
    private route: ActivatedRoute,
    private matchService: MatchService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.matchService.getMatchById(id).subscribe(match => this.match = match);
    } else {
      console.error('Match ID is null');
    }
  }
}