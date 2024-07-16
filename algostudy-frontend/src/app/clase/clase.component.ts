import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Clasa } from '../probleme/shared/clasa.model';
import { ClasaService } from '../probleme/shared/clasa.service';

@Component({
  selector: 'app-clase',
  templateUrl: './clase.component.html',
  styleUrls: ['./clase.component.css']
})
export class ClaseComponent implements OnInit {

    clase: Clasa[];

    constructor(private clasaService: ClasaService, private router: Router) {this.clase = [] }
  
    ngOnInit(): void {
      this.clasaService.getClase().subscribe(
        clase => this.clase = clase
      );
    }

    navigateToProblem(id: string | number): void { // Define the navigation function
        this.router.navigate(['/clasa', id]);
    }

}
