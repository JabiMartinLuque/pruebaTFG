import { Component } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { UsuarioService } from '../services/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicio-sesion',
  templateUrl: './inicio-sesion.component.html',
  styleUrls: ['./inicio-sesion.component.css']
})
export class InicioSesionComponent {

  usuario: Usuario = new Usuario('', '', '');
  errorMessage: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  iniciarSesion() {
    console.log('Iniciando sesión para usuario:', this.usuario);

    this.usuarioService.iniciarSesion(this.usuario).subscribe(
      response => {
        console.log('Inicio de sesión exitoso', response);
        localStorage.setItem('token', response.token); // Guarda el token en el localStorage
        this.router.navigate(['/']);  // Redirigir al usuario al componente app
      },
      error => {
        console.error('Error al iniciar sesión', error);
        this.errorMessage = 'Credenciales incorrectas';
      }
    );
  }
}