export interface Problem {
  id: number;
  title: string;
  description: string;
  inputExample: string;
  outputExample: string;
  points: number;
  pointsToUnlockCategory: number;
  pointsToUnlockTotal: number;
  categoryId: number;
}
  
  export interface ProblemCreate {
    title: string;
    description: string;
    inputExample: string;
    outputExample: string;
    difficulty: string;
    points: number;
    pointsToUnlockCategory: number;
    pointsToUnlockTotal: number;
    categoryId: string; // Assuming category ID is passed during creation
  }
  