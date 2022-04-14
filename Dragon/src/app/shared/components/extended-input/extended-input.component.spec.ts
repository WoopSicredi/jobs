import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtendedInputComponent } from './extended-input.component';

describe('ExtendedInputComponent', () => {
  let component: ExtendedInputComponent;
  let fixture: ComponentFixture<ExtendedInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExtendedInputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtendedInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
