import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Clasa } from '../../probleme/shared/clasa.model';
import { ClasaService } from '../../probleme/shared/clasa.service';
import { SolutieService } from '../../shared/solution.service';
import { Solution } from '../../shared/solution.model';
import { TemaService } from '../../shared/tema.service';
import { Tema } from '../../shared/tema.model';
import { UserClasaService } from '../../shared/userclasa.service';

@Component({
  selector: 'app-clase',
  templateUrl: './clase-detail.component.html',
  styleUrls: ['./clase-detail.component.css']
})
export class ClasaDetailComponent implements OnInit {

    clasa: Clasa = {
        nivel: '',
        localitate: '',
        nume: '',
        unitateScolara: '',
        id: 0,
        judet: ''
    };

    userId = ""
    teme: Tema[] = []

    constructor(
        private clasaService: ClasaService, 
        private router: Router, 
        private route: ActivatedRoute,
        private userClasaService: UserClasaService,
        private temaService: TemaService) {}
  
    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            const id: string = params.get('id')!;
            this.clasaService.getClasa(id).subscribe(
                clasa => this.clasa = clasa
            );

            this.temaService.getTeme().subscribe(
                teme => this.teme = teme.filter(tema => tema.clasa.id == this.clasa.id)
            )
        });

    }

    adaugaElev(): void {
        this.userClasaService.save({idUser: +this.userId, idClasa: this.clasa.id}).subscribe({
        next: (response) => {
            console.log(response)
            this.userId = '';
        },
        error: (error) => {
            console.error(error);
        }
        });
        
      }

    deleteClasa(): void { // Define the navigation function
        this.clasaService.deleteClasa(this.clasa.id.toString()).subscribe();
        this.router.navigate(['/clase']);
    }
}
