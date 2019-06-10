
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

  protected _dragon     : DragonModel
  protected _hasError   : boolean
  protected _hasNotice  : boolean
  protected _message    : string
  protected _pending    : boolean
  protected _service    : DragonService



  constructor (
    service : DragonService
  , auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super (auth, route, router)

    this.service  = service

    this.onDismissToast = this.onDismissToast.bind(this)

  }



  ngOnInit () 
  {

  }



  protected onDismissToast ()
  {

    this.hasError = this.hasNotice = false
    this.message  = null

  }



  protected get dragon ()
  {
    return (this._dragon)
  }

  protected get hasError ()
  {
    return (this._hasError)
  }

  protected get hasNotice ()
  {
    return (this._hasNotice)
  }

  protected get message ()
  {
    return (this._message)
  }

  protected get pending ()
  {
    return (this._pending)
  }

  protected get service ()
  {
    return (this._service)
  }



  protected set dragon (dragon)
  {
    this._dragon = dragon
  }

  protected set hasError (hasError)
  {
    this._hasError = hasError
  }

  protected set hasNotice (hasNotice)
  {
    this._hasNotice = hasNotice
  }

  protected set message (message)
  {
    this._message = message
  }

  protected set pending (pending)
  {
    this._pending = pending
  }
  
  protected set service (service)
  {
    this._service = service
  }

}
