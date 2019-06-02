
import { ActivatedRoute, Router } from '@angular/router'
import { Component, OnInit }      from '@angular/core'

import { AuthService }            from '../../../dragon-app-common/auth/services/auth.service'
import { DragonModel }            from '../../models/dragon.model'
import { DragonService }          from '../../services/dragon.service'
import { LoggedInPageComponent }  from '../../../dragon-app-common/pages/logged-in-page/logged-in-page.component'






@Component({
  selector:     'dragon-dragon-page'
, templateUrl:  './dragon-page.component.html'
, styleUrls:    ['./dragon-page.component.scss']
})
export class DragonPageComponent extends LoggedInPageComponent implements OnInit 
{

  private _dragon     : DragonModel
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

  }



  ngOnInit () 
  {

  }



  protected onDismissError ()
  {

    this.hasError   = false
    this.lastError  = 'Não foi possível excluir o dragão'

  }



  public get dragon ()
  {
    return (this._dragon)
  }

  public get hasError ()
  {
    return (this._hasError)
  }

  public get lastError ()
  {
    return (this._lastError)
  }

  protected get service ()
  {
    return (this._service)
  }



  public set dragon (dragon)
  {
    this._dragon = dragon
  }

  public set hasError (hasError)
  {
    this._hasError = hasError
  }

  public set lastError (lastError)
  {
    this._lastError = lastError
  }

  protected set service (service)
  {
    this._service = service
  }

}
