
import { ActivatedRoute, Router } from '@angular/router'
import { Component, OnInit }      from '@angular/core'

import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch'

import { AuthService }            from '../../../dragon-app-common/auth/services/auth.service';
import { DragonModel }            from '../../models/dragon.model';
import { DragonService }          from '../../services/dragon.service';
import { LoggedInPageComponent }  from '../../../dragon-app-common/pages/logged-in-page/logged-in-page.component'
import { DragonError } from '../../errors/dragon-error';
import { DragonNotFoundError } from '../../errors/dragon-not-found-error';
import { Observable } from 'rxjs/Observable';






@Component({
  selector:     'dragon-dragon-list'
, templateUrl:  './dragon-list.component.html'
, styleUrls:    ['./dragon-list.component.scss']
})
export class DragonListComponent extends LoggedInPageComponent implements OnInit 
{

  private _dragons    : DragonModel[]
  private _hasError   : boolean
  private _lastError  : string
  private _service    : DragonService



  constructor (
    service : DragonService
  , auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super (auth, route, router)

    this.service  = service

    this.onDismissError = this.onDismissError.bind(this)
    this.onGet          = this.onGet.bind(this)

  }



  ngOnInit () 
  {

    this.service
    .getDragons()
    .subscribe( this.onGet )

  }



  private onEdit ($event, dragon : DragonModel)
  {

    $event.preventDefault()
    $event.stopPropagation()

    console.debug(dragon)

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
    console.debug(dragon)

  }



  private onDismissError ()
  {

    console.debug('TURANDO OS ERRO')
    this.hasError   = false
    this.lastError  = 'Não foi possível excluir o dragão'

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

  public get hasError ()
  {
    return (this._hasError)
  }

  public get lastError ()
  {
    return (this._lastError)
  }

  private get service ()
  {
    return (this._service)
  }



  public set dragons (dragons)
  {
    this._dragons = dragons
  }

  public set hasError (hasError)
  {
    this._hasError = hasError
  }

  public set lastError (lastError)
  {
    this._lastError = lastError
  }

  private set service (service)
  {
    this._service = service
  }

}
