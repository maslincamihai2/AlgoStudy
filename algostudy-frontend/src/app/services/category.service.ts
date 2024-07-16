import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/categories';

  constructor(private httpClient: HttpClient, private tokenService: TokenService) {}

  getCategories(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.apiUrl, {
      headers: { Authorization: `Bearer ${this.tokenService.getToken()}` }
    });
  }

  getCategory(id: string): Observable<any> {
    return this.httpClient.get<any>(`${this.apiUrl}/${id}`, {
      headers: { Authorization: `Bearer ${this.tokenService.getToken()}` }
    });
  }
}
