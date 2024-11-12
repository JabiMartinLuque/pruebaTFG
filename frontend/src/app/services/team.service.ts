import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private apiUrl = 'http://localhost:8080/api/teams';  // URL del backend Spring Boot

  constructor(private http: HttpClient) {}

  getTeams(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getTeamNameByHref(href: string): Observable<any> {
    return this.http.get<any>(href).pipe(
      map(response => ({
        name: response.name
      }))
    );
  }

  getTeamLogoByHref(href: string): Observable<any> {
    return this.http.get<any>(href).pipe(
      map(response => response.logos[0].href)
    );
  }

  getTeamIdByHref(href: string): Observable<string> {
    console.log('Getting team ID by href:', href);
    return this.http.get<any>(href).pipe(
      map(response => response.id),
      tap(id => console.log('Team ID:', id))
    );
  }

  getTeamById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}