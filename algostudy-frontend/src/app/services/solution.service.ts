import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolutionService {
  private apiUrl = 'http://localhost:8080/api/solutions';

  constructor(private httpClient: HttpClient) {}

  getSolutionsByUser(userId: number): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.apiUrl}/user/${userId}`);
  }

  getSolutionsByProblem(problemId: number): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.apiUrl}/problem/${problemId}`);
  }

  submitSolution(solution: any): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl, solution);
  }
}
