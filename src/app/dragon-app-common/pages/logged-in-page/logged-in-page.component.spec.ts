import { async, ComponentFixture, TestBed } from '@angular/core/testing'

import { LoggedInPageComponent } from './logged-in-page.component'





describe ('LoggedInPageComponent', () => 
{

  let component : LoggedInPageComponent
  let fixture   : ComponentFixture<LoggedInPageComponent>



  beforeEach (async(() => 
  {

    TestBed.configureTestingModule({
      declarations: [ LoggedInPageComponent ]
    })
    .compileComponents()

  }))



  beforeEach (() => 
  {

    fixture   = TestBed.createComponent(LoggedInPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()

  })



  it ('should create', () => 
  {
    expect(component).toBeTruthy()
  })

})
