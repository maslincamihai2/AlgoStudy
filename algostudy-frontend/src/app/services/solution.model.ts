export interface Solution {
    id: number;
    code: string;
    score: number;
    userId: number;
    problemId: number;
  }
  
  export interface SolutionCreate {
    code: string;
    score: number;
    userId: number;
    problemId: number;
  }