import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseErgebnisComponent } from './analyse-ergebnis.component';

describe('AnalyseErgebnisComponent', () => {
  let component: AnalyseErgebnisComponent;
  let fixture: ComponentFixture<AnalyseErgebnisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyseErgebnisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyseErgebnisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
