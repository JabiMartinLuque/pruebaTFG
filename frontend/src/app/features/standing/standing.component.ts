import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StandingService } from './standing.service';
import { TeamService } from '../team/team.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-standing',
  templateUrl: './standing.component.html',
  styleUrl: './standing.component.css'
})
export class StandingComponent {
  standingsESP: any[] = [];
  standingsENG: any[] = [];
  currentStandings: any[] = [];
  teamCache: { [key: string]: { name: string, logo: string } } = {};

  constructor(private router: Router, private standingService: StandingService, private teamService: TeamService) { }

  ngOnInit(): void {
    this.standingService.getStandingsEsp().subscribe(
      standingsESP => {
        this.standingsESP = standingsESP;
        console.log('Clasificaciones:', this.standingsESP);
      },
      error => {
        console.error('Error al obtener las clasificaciones:', error);
      }
    );

    this.standingService.getStandingsEng().subscribe(
      standingsENG => {
        this.standingsENG = standingsENG;
        console.log('Standings:', this.standingsENG);
      },
      error => {
        console.error('Error getting standings:', error);
      }
    );
  }

  showEsp(): void {
    this.currentStandings = this.standingsESP;
    this.loadTeamDetails();
  }

  showEng(): void {
    this.currentStandings = this.standingsENG;
    this.loadTeamDetails();
  }

  loadTeamDetails(): void {
    this.currentStandings.forEach(standing => {
      const teamHref = standing.team.$ref;
      if (!this.teamCache[teamHref]) {
        this.getTeamNameByHref(teamHref).subscribe(
          (          teamDetails: { name: any; logo: any; }) => {
            this.teamCache[teamHref] = { name: teamDetails.name, logo: teamDetails.logo };
            standing.teamName = teamDetails.name;

          },
          (          error: any) => {
            console.error('Error getting team details:', error);
          }
        );
        this.getTeamLogoByHref(teamHref).subscribe(
          (          teamDetails: any) => {
            this.teamCache[teamHref].logo = teamDetails;
            standing.teamLogo = teamDetails;
          },
          (          error: any) => {
            console.error('Error getting team logo:', error);
          }
        );
      } else {
        standing.teamName = this.teamCache[teamHref].name;
        standing.teamLogo = this.teamCache[teamHref].logo;
      }
    });
  }

  getTeamNameByHref(href: string): Observable<any> {
    return this.teamService.getTeamNameByHref(href);
  }

  getTeamLogoByHref(href: string): Observable<any> {
    return this.teamService.getTeamLogoByHref(href);
  }

  goToTeamDetail(href: string): void {
    console.log('Navigating to team:', href);
    this.teamService.getTeamIdByHref(href).subscribe(id => {
      console.log('Team ID:', id);
      this.router.navigate(['/equipo', id]);
    });
  }
}
