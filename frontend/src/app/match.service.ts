import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl = 'http://localhost:8080/api/matches';  // URL del backend Spring Boot

  constructor(private http: HttpClient) {}

  getMatches(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}