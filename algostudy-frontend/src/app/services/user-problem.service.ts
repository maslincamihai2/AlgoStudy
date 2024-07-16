import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserProblemService {
  private apiUrl = 'http://localhost:8080/api/user-problems';

  constructor(private httpClient: HttpClient) {}

  getUserProblemsByUserId(userId: number): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.apiUrl}/user/${userId}`);
  }
}
