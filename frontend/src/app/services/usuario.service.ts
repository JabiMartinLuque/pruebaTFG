import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../entities/usuario';
import { Observable } from 'rxjs';
import { AuthResponse } from '../entities/auth-response';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

    private apiURL = 'http://localhost:8080/api/usuarios';

    constructor(private http: HttpClient) { }
  
    registrarUsuario(usuario: Usuario): Observable<AuthResponse> {
      return this.http.post<AuthResponse>(this.apiURL + '/registro', usuario);
    }
  
    obtenerUsuarios(): Observable<AuthResponse[]> {
      return this.http.get<AuthResponse[]>(this.apiURL);
    }

    iniciarSesion(usuario: Usuario): Observable<any> {
        return this.http.post<any>(this.apiURL, usuario);  // Cambia a la URL correcta según tu configuración
    }
}
