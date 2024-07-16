import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProblemService } from '../services/problem.service';
import { SolutionService } from '../services/solution.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-problem-detail',
  templateUrl: './problem-detail.component.html',
  styleUrls: ['./problem-detail.component.css']
})
export class ProblemDetailComponent implements OnInit {
  problem: any;
  solutionCode: string = '';
  problemId: string = '';
  userId: number = 0;
  solutions: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private problemService: ProblemService,
    private solutionService: SolutionService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.problemId = this.route.snapshot.paramMap.get('id') || '';
    this.authService.getUserId().subscribe(userId => {
      this.userId = userId ? userId : 0;
      this.loadProblem();
      this.loadSolutions();
    });
  }

  loadProblem() {
    this.problemService.getProblem(this.problemId).subscribe(problem => {
      this.problem = problem;
    });
  }

  loadSolutions() {
    this.solutionService.getSolutionsByUser(this.userId).subscribe(solutions => {
      this.solutions = solutions.filter(solution => solution.problemId === +this.problemId);
    });
  }

  submitSolution() {
    const solution = {
      problemId: +this.problemId,
      code: this.solutionCode,
      score: 100,  // Assuming a default score, you might want to handle it accordingly
      userId: this.userId
    };
    this.solutionService.submitSolution(solution).subscribe(response => {
      console.log('Solution submitted successfully');
      this.loadSolutions();  // Reload solutions after submitting
    });
  }
}
