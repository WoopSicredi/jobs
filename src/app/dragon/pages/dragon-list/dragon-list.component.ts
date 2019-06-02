
import { ActivatedRoute, Router } from '@angular/router'
import { Component, OnInit }      from '@angular/core'

import { AuthService }            from '../../../dragon-app-common/auth/services/auth.service'
import { DragonError }            from '../../errors/dragon-error'
import { DragonModel }            from '../../models/dragon.model'
import { DragonService }          from '../../services/dragon.service'
import { DragonPageComponent } from '../dragon-page/dragon-page.component'






@Component({
  selector:     'dragon-dragon-list'
, templateUrl:  './dragon-list.component.html'
, styleUrls:    ['./dragon-list.component.scss']
})
export class DragonListComponent extends DragonPageComponent implements OnInit 
{

  private _dragons    : DragonModel[]



  constructor (
    service : DragonService
  , auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super (service, auth, route, router)

    this.onError  = this.onError.bind(this)
    this.onGet    = this.onGet.bind(this)

  }



  ngOnInit () 
  {

    this.service
    .getDragons()
    .subscribe( 
      this.onGet
    , this.onError
    )

  }



  private onError (error : DragonError)
  {

    this.hasError   =  true
    this.lastError  = 'Não foi possível recuperar a lista de dragões'

  }



  private onConfirmNo (dragon : DragonModel)
  {
    delete(dragon['pending'])
  }



  private onConfirmYes (dragon : DragonModel)
  {
    
    const index = this.dragons.indexOf(dragon)
    let   deletedDragon : DragonModel

    if (index >= 0) {
      deletedDragon = (<DragonModel> this.dragons.splice(index, 1)[0])
    }

    this.service
    .deleteDragon(deletedDragon)
    .subscribe( 
      
      (response) => console.debug(response)
  
    , (error)   =>  
      {

        this.dragons.splice(index, 0, deletedDragon)
        this.hasError   = true
        this.lastError  = 'Não foi possível excluir o dragão'
        
      }
      
    )

  }



  private onDelete (dragon : DragonModel)
  {

    this.onDismissError()
    this.dragons.map( (dragon) => delete(dragon['pending']) )
    dragon['pending']  = true

  }



  private onGet (dragons : DragonModel[])
  {
    
    dragons.sort( (a, b) => a.name.localeCompare(b.name) )
    this.dragons  = dragons
    
  }



  public get dragons ()
  {
    return (this._dragons)
  }



  public set dragons (dragons)
  {
    this._dragons = dragons
  }

}
