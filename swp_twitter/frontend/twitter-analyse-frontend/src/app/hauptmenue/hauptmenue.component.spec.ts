import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HauptmenueComponent } from './hauptmenue.component';

describe('HauptmenueComponent', () => {
  let component: HauptmenueComponent;
  let fixture: ComponentFixture<HauptmenueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HauptmenueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HauptmenueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
