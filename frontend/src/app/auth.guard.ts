// src/app/auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const isAuthenticated = !!localStorage.getItem('token'); // O tu lógica de autenticación
    if (!isAuthenticated) {
      this.router.navigate(['/inicio-sesion']);
      return false;
    }
    return true;
  }
}