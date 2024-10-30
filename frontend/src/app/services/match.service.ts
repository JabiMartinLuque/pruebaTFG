import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl = 'http://localhost:8080/api/matches';  // URL del backend Spring Boot
  private matchesCache: any[] | null = null;


  constructor(private http: HttpClient) {}

  getMatches(): Observable<any[]> {
    if (this.matchesCache) {
      return of(this.matchesCache);
    } else {
      return this.http.get<any>(this.apiUrl).pipe(
        map(response => response.matches), // Asegúrate de que la respuesta sea un array de partidos
        tap(matches => this.matchesCache = matches)
      );
    }
  }
  getMatchById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  getMatchesEsp(): Observable<any[]> {
    return this.http.get<any>(this.apiUrl + '/esp').pipe(
      map(response => response.events),
    );
  }

  getMatchesEng(): Observable<any[]> {
    return this.http.get<any>(this.apiUrl + '/eng').pipe(
      map(response => response.events),
    );
  }
}