import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClasaDetailComponent } from './clase-detail.component';

describe('ClaseDetailComponent', () => {
  let component: ClasaDetailComponent;
  let fixture: ComponentFixture<ClasaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClasaDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClasaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
