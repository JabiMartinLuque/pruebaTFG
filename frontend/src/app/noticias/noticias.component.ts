import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NoticiaService } from '../services/noticia.service';

@Component({
  selector: 'app-noticias',
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.css'
})
export class NoticiasComponent {
  
    noticias: any[] = [];
  
    constructor(private noticiaService: NoticiaService) {}
  
    ngOnInit(): void {
      this.noticiaService.getNoticiasEsp().subscribe(
        noticias => this.noticias = noticias
      );
    }
}
