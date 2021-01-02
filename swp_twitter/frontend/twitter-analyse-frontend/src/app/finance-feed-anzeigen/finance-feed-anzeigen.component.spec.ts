import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceFeedsAnzeigenComponent } from './finance-feeds-anzeigen.component';

describe('FinanceFeedsAnzeigenComponent', () => {
  let component: FinanceFeedsAnzeigenComponent;
  let fixture: ComponentFixture<FinanceFeedsAnzeigenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinanceFeedsAnzeigenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceFeedsAnzeigenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
