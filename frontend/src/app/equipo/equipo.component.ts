import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'app-equipo',
  templateUrl: './equipo.component.html',
  styleUrl: './equipo.component.css'
})
export class EquipoComponent {
  team: any;

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.teamService.getTeamById(id).subscribe(team => this.team = team);
    } else {
      console.error('Team ID is null');
    }
  }
}
