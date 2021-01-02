import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceFeedAnlegenComponent } from './finance-feed-anlegen.component';

describe('FinanceFeedAnlegenComponent', () => {
  let component: FinanceFeedAnlegenComponent;
  let fixture: ComponentFixture<FinanceFeedAnlegenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinanceFeedAnlegenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceFeedAnlegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
