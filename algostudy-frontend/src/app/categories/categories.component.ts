import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../services/category.service';
import { ProblemService } from '../services/problem.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  categories: any[] = [];
  userCategoryPoints: { [key: number]: any } = {};

  constructor(
    private categoryService: CategoryService,
    private problemService: ProblemService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
      this.categories.forEach(category => {
        this.problemService.getCategoryPoints(category.id).subscribe(pointsData => {
          this.userCategoryPoints[category.id] = pointsData;
        });
      });
    });
  }

  isLoggedIn(): boolean {
    return !!this.authService.getToken();
  }

  viewProblems(categoryId: number) {
    this.router.navigate([`/problems/${categoryId}`]);
  }
}
