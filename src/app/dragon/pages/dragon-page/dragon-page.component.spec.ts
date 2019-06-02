import { async, ComponentFixture, TestBed } from '@angular/core/testing'

import { DragonPageComponent } from './dragon-page.component'





describe('DragonPageComponent', () => 
{

  let component : DragonPageComponent
  let fixture   : ComponentFixture<DragonPageComponent>



  beforeEach (async(() => 
  {

    TestBed.configureTestingModule({
      declarations: [ DragonPageComponent ]
    })
    .compileComponents()

  }))



  beforeEach (() => 
  {

    fixture   = TestBed.createComponent(DragonPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()

  })



  it ('should create', () => 
  {
    expect(component).toBeTruthy()
  })

})
