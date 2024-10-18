// src/app/registro/registro.component.ts
import { Component, EventEmitter, Output } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { UsuarioService } from '../services/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {

  @Output() registroCompletado = new EventEmitter<void>();

  usuario: Usuario = new Usuario('', '', '');

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  registrar() {
    console.log('Registrando usuario:', this.usuario);

    this.usuarioService.registrarUsuario(this.usuario).subscribe(
      response => {
        console.log('Usuario registrado con éxito', response);
        localStorage.setItem('token', response.token); // Guarda el token en el localStorage
        this.router.navigate(['/']);  // Redirigir al usuario al componente app
        this.registroCompletado.emit(); // Emitir evento de registro completado
      },
      error => {
        console.error('Error al registrar el usuario', error);
      }
    );
  }
}