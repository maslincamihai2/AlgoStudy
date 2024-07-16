import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';  // You might need to install this package

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly TOKEN_KEY = 'auth_token';
  private jwtHelper = new JwtHelperService();

  constructor() { }

  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getUserId(): number | null {
    const token = this.getToken();
    console.log("TOKEN ", token)
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.sub;  // Assuming the token contains a userId field
    }
    return null;
  }
}
