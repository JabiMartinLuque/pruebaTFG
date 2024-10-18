import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'furboaaaaa';

    // Bandera para saber si el usuario está registrado
    usuarioRegistrado: boolean = false;

    // Este método será llamado cuando el usuario se registre
    onRegistroCompletado() {
      this.usuarioRegistrado = true;  // Cambia la bandera a 'true'
    }
}
