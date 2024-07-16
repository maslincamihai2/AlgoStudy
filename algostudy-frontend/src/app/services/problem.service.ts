import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Problem } from './problem.model';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {
  private apiUrl = 'http://localhost:8080/api/problems';

  constructor(private httpClient: HttpClient) {}

  getProblems(): Observable<Problem[]> {
    return this.httpClient.get<Problem[]>(this.apiUrl);
  }

  getProblemsByCategory(categoryId: string): Observable<Problem[]> {
    return this.httpClient.get<Problem[]>(`${this.apiUrl}/category/${categoryId}`);
  }

  getCategoryPoints(categoryId: string): Observable<any> {
    return this.httpClient.get<any>(`${this.apiUrl}/category/${categoryId}/points`);
  }

  getProblem(id: string): Observable<Problem> {
    return this.httpClient.get<Problem>(`${this.apiUrl}/${id}`);
  }

  createProblem(problem: Problem): Observable<Problem> {
    return this.httpClient.post<Problem>(this.apiUrl, problem);
  }

  updateProblem(id: string, problem: Problem): Observable<Problem> {
    return this.httpClient.put<Problem>(`${this.apiUrl}/${id}`, problem);
  }

  deleteProblem(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`);
  }

  getUserProblems(userId: string): Observable<any[]> {
    return this.httpClient.get<any[]>(`http://localhost:8080/api/user-problems/user/${userId}`);
  }
}
