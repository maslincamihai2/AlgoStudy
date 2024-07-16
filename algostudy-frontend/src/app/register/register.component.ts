import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username = '';
  email = '';
  password = '';
  onboardingQuestions = [
    'How familiar are you with programming and algorithms?',
    'How familiar are you with graph theory?',
    'How familiar are you with dynamic programming algorithms?',
    'How familiar are you with data structures?',
    'How familiar are you with greedy algorithms?'
  ];
  ratings = [0, 1, 2, 3, 4, 5];
  onboardingScores = [0, 0, 0, 0, 0];

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  selectRating(questionIndex: number, rating: number) {
    this.onboardingScores[questionIndex] = rating;
  }

  isSurveyComplete(): boolean {
    return this.onboardingScores.every(score => score >= 0);
  }

  onSubmit() {
    const onboardingScore = this.onboardingScores.reduce((a, b) => a + b, 0);
    this.authService.register(this.username, this.email, this.password, onboardingScore).subscribe(response => {
      this.router.navigate(['/login']);
    });
  }
}
