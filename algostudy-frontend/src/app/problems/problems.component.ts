import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProblemService } from '../services/problem.service';
import { UserProblemService } from '../services/user-problem.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {
  problems: any[] = [];
  solvedProblems: number[] = [];
  categoryId: string = '';
  userId: number | null = 0;

  constructor(
    private route: ActivatedRoute,
    private problemService: ProblemService,
    private userProblemService: UserProblemService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.categoryId = this.route.snapshot.paramMap.get('id') || '';
    this.authService.getUserId().subscribe(userId => {
      if (userId !== null) {
        this.userId = userId;
        this.loadProblems();
      } else {
        // Handle case where userId is null
        console.error('User is not logged in');
      }
    });
  }

  loadProblems() {
    this.problemService.getProblemsByCategory(this.categoryId).subscribe(problems => {
      this.problems = problems;
      if (this.userId !== null) {
        this.userProblemService.getUserProblemsByUserId(this.userId).subscribe((userProblems: any[]) => {
          this.solvedProblems = userProblems.filter(up => up.highestScore === 100).map(up => up.problem.id);
        });
      }
    });
}

isSolved(problemId: number): boolean {
  return this.solvedProblems.includes(problemId);
}
}
