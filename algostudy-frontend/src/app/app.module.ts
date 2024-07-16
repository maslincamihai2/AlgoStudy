import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './categories/categories.component';
import { ProblemsComponent } from './problems/problems.component';
import { ProblemDetailComponent } from './problem-detail/problem-detail.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthInterceptor } from './auth.interceptor';
import { TokenService } from './token.service';
import { AuthService } from './services/auth.service';
import { CategoryService } from './services/category.service';
import { ProblemService } from './services/problem.service';
import { SolutionService } from './services/solution.service';
import { UserProblemService } from './services/user-problem.service';

@NgModule({
  declarations: [
    AppComponent,
    CategoriesComponent,
    ProblemsComponent,
    ProblemDetailComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    TokenService,
    AuthService,
    CategoryService,
    ProblemService,
    SolutionService,
    UserProblemService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
