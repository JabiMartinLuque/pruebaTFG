import { Component, EventEmitter, Output } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {

  @Output() registroCompletado = new EventEmitter<void>();  // Emitir evento al registrarse

  usuario: Usuario = new Usuario('', '', '');

  constructor(private usuarioService: UsuarioService) { }

  registrar() {
    console.log('Registrando usuario:', this.usuario);

    // Usar el servicio para enviar el usuario al backend
    this.usuarioService.registrarUsuario(this.usuario).subscribe(
      response => {
        console.log('Usuario registrado con éxito', response);
        this.registroCompletado.emit();  // Emitir evento de registro completado
      },
      error => {
        console.error('Error al registrar el usuario', error);
      }
    );
  }
}
