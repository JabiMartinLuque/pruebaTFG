import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../entities/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

    private apiURL = 'http://localhost:8080/api/usuarios';

    constructor(private http: HttpClient) { }
  
    registrarUsuario(usuario: Usuario): Observable<Usuario> {
      return this.http.post<Usuario>(this.apiURL + '/registro', usuario);
    }
  
    obtenerUsuarios(): Observable<Usuario[]> {
      return this.http.get<Usuario[]>(this.apiURL);
    }

    iniciarSesion(usuario: Usuario): Observable<any> {
        return this.http.post<any>(this.apiURL + '/login', usuario);  // Cambia a la URL correcta según tu configuración
    }
}
