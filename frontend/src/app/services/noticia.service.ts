import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})

export class NoticiaService {
    
      private apiUrl = 'http://localhost:8080/api/noticias';  // URL del backend Spring Boot
      private noticiasCache: any[] | null = null;

        constructor(private http: HttpClient) {}

        getNoticiasEsp(): Observable<any[]> {
          if (this.noticiasCache) {
            return of(this.noticiasCache);
          } else {
            return this.http.get<any>(this.apiUrl + '/esp').pipe(
                map(response => response.articles),
                tap(articles => this.noticiasCache = articles)
              );
          }
        }
}