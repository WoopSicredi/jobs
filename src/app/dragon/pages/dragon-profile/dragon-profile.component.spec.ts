import { async, ComponentFixture, TestBed } from '@angular/core/testing'

import { DragonProfileComponent } from './dragon-profile.component'





describe ('DragonProfileComponent', () => 
{

  let component : DragonProfileComponent
  let fixture   : ComponentFixture<DragonProfileComponent>



  beforeEach (async(() => 
  {

    TestBed.configureTestingModule({
      declarations: [ DragonProfileComponent ]
    })
    .compileComponents()

  }))



  beforeEach (() => 
  {

    fixture   = TestBed.createComponent(DragonProfileComponent)
    component = fixture.componentInstance
    fixture.detectChanges()

  })



  it ('should create', () => 
  {
    expect(component).toBeTruthy()
  })

})
