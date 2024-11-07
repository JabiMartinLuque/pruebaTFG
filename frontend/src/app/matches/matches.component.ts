import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
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

  displayedDates: any[] = [];
  selectedDate: string = '';

  @ViewChild('scrollContainer') scrollContainer!: ElementRef;


  constructor(private router: Router, private matchService: MatchService) { }

  ngOnInit(): void {
    /*
    this.matchService.getMatches().subscribe(
      matches => {
        this.matches = matches;
        console.log('Matches:', this.matches);
      },
      error => {
        console.error('Error al obtener los matches:', error);
      }
    );
    */
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

    this.generateDates();
    this.selectedDate = this.formatDateValue(new Date());
  }

  ngAfterViewInit() {
    this.centerScroll();
  }

  goToMatchDetail(id: string): void {
    this.router.navigate(['/matches', id]);
  }

  generateDates() {
    const today = new Date();
    const displayedDates = [];

    for (let i = -20; i <= 20; i++) {
      const date = new Date();
      date.setDate(today.getDate() + i);
      displayedDates.push({
        date: date,
        display: this.isToday(date) ? 'Hoy' : this.formatDateDisplay(date),
        value: this.formatDateValue(date)
      });
    }

    this.displayedDates = displayedDates;
  }

  formatDateValue(date: Date): string {
    const year = date.getFullYear();
    const month = this.padZero(date.getMonth() + 1);
    const day = this.padZero(date.getDate());
    return `${year}${month}${day}`;
  }

  formatDateDisplay(date: Date): string {
    const options = { weekday: 'short', day: 'numeric', month: 'short' } as const;
    return date.toLocaleDateString('es-ES', options);
  }

  padZero(n: number): string {
    return n < 10 ? '0' + n : n.toString();
  }

  onDateSelected(dateObj: any) {
    this.selectedDate = dateObj.value;
    console.log('Fecha seleccionada:', this.selectedDate);
    this.matchService.getMatchesByDateEsp(this.selectedDate).subscribe(matches => {
      this.matchesESP = matches;
      console.log('Partidos:', this.matches);
    });
    this.matchService.getMatchesByDateEng(this.selectedDate).subscribe(matches => {
      this.matchesENG = matches;
      console.log('Matches:', this.matches);
    });
  }

  isSelectedDate(dateObj: any): boolean {
    return dateObj.value === this.selectedDate;
  }

  isToday(date: Date): boolean {
    const today = new Date();
    return date.getDate() === today.getDate() &&
           date.getMonth() === today.getMonth() &&
           date.getFullYear() === today.getFullYear();
  }

  centerScroll() {
    const container = this.scrollContainer.nativeElement;
    const activeButton = container.querySelector('.date-button.active');
    if (activeButton) {
      const containerWidth = container.offsetWidth;
      const buttonWidth = activeButton.offsetWidth;
      const buttonOffsetLeft = activeButton.offsetLeft;
      const scrollPosition = buttonOffsetLeft - (containerWidth / 2) + (buttonWidth / 2);
      container.scrollLeft = scrollPosition;
    }
  }
}