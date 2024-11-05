import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
    })
export class StandingService {
    
        private apiUrl = 'http://localhost:8080/api/standing';  // URL del backend Spring Boot
        private standingsCache: any[] | null = null;
    
        constructor(private http: HttpClient) {}
    
        getStandings(): Observable<any[]> {
            if (this.standingsCache) {
                return of(this.standingsCache);
            } else {
                return this.http.get<any>(this.apiUrl).pipe(
                    map(response => response.standings), // Asegúrate de que la respuesta sea un array de clasificaciones
                    tap(standings => this.standingsCache = standings)
                );
            }
        }
    
        getStandingById(id: string): Observable<any> {
            return this.http.get<any>(`${this.apiUrl}/${id}`);
        }
    
        getStandingsEsp(): Observable<any[]> {
            return this.http.get<any>(this.apiUrl + '/esp').pipe(
                map(response => response.standings),
            );
        }
    
        getStandingsEng(): Observable<any[]> {
            return this.http.get<any>(this.apiUrl + '/eng').pipe(
                map(response => response.standings),
            );
        }
    }