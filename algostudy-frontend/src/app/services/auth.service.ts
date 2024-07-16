import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators'; // Import the map operator
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient, private tokenService: TokenService) {}

  login(email: string, password: string): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/login`, { email, password }).pipe(
      map((response: any) => {
        this.tokenService.saveToken(response);
        return response;
      })
    );
  }

  register(username: string, email: string, password: string, onboardingScore: number): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/register`, { username, email, password, onboardingScore });
  }

  logout() {
    this.tokenService.removeToken();
  }

  getToken() {
    return this.tokenService.getToken();
  }

  getUserId(): Observable<number | null> {
    const token = this.tokenService.getToken();
    if (token) {
      return this.httpClient.get<number>(`${this.apiUrl}/id`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return of(null);
  }
}
