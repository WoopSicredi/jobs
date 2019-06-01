import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonFormComponent } from './dragon-form.component';

describe('DragonFormComponent', () => {
  let component: DragonFormComponent;
  let fixture: ComponentFixture<DragonFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
