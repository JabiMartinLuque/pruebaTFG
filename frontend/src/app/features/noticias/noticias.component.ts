import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NoticiasService } from './noticias.service';

@Component({
  selector: 'app-noticias',
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.css'
})
export class NoticiasComponent {
  
    noticias: any[] = [];
  
    constructor(private noticiasService: NoticiasService) {}
  
    ngOnInit(): void {
      this.noticiasService.getNoticiasEsp().subscribe(
        noticias => this.noticias = noticias
      );
    }
}
