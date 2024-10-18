import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';  // Asegúrate de importar el servicio
import { Usuario } from '../entities/usuario';

@Component({
  selector: 'app-inicio-sesion',
  templateUrl: './inicio-sesion.component.html',
  styleUrls: ['./inicio-sesion.component.css']
})
export class InicioSesionComponent {

  usuario: Usuario = new Usuario('', '', '');
  errorMessage: string | null = null; // Para mostrar el mensaje de error
    

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  iniciarSesion() {
    this.usuarioService.iniciarSesion(this.usuario).subscribe(
      response => {
        console.log('Inicio de sesión exitoso', response);
        // Redirigir al usuario al inicio después de iniciar sesión
        this.router.navigate(['/']);  // Cambia la ruta según tu configuración
      },
      error => {
        console.error('Error al iniciar sesión', error);
        this.errorMessage = 'Usuario o contraseña incorrectos. Por favor, intenta de nuevo.';
      }
    );
  }
}
