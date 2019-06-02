
import { ActivatedRoute, ParamMap, Router  }  from '@angular/router'
import { Component, OnInit }                  from '@angular/core'
import { FormGroup, FormControl, Validators }                          from '@angular/forms'

import { AuthService }          from '../../../dragon-app-common/auth/services/auth.service'
import { DragonPageComponent }  from '../dragon-page/dragon-page.component'
import { DragonService }        from '../../services/dragon.service'
import { DragonModel } from '../../models/dragon.model';







@Component({
  selector:     'dragon-dragon-form'
, templateUrl:  './dragon-form.component.html'
, styleUrls:    ['./dragon-form.component.scss']
})
export class DragonFormComponent extends DragonPageComponent implements OnInit 
{

  private _action   : string
  private _form     : FormGroup



  constructor (
    service : DragonService
  , auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super (service, auth, route, router)

    this.getParams  = this.getParams.bind(this)
    this.onCreate   = this.onCreate.bind(this)

  }



  ngOnInit () 
  {
    
    this.route
    .paramMap
    .subscribe( this.getParams )

  }



  private afterSend ()
  {

    this.pending  = false
    this.form.enable()

  }



  private beforeSend ()
  {

    this.pending  = true
    this.form.disable()

  }



  private createForm (dragon ?: DragonModel)
  {

    this.form = new FormGroup({

      name: new FormControl(

        ((dragon) ? (dragon.name) : ('dragao_01'))

      , [ 
          Validators.pattern(/[^\s]+[^\s]$/)
        , Validators.required
        ]

      )

    , type: new FormControl(

        ((dragon) ? (dragon.type) : ('tipo_01'))

      , [
          Validators.pattern(/[^\s]+[^\s]$/)
        , Validators.required
        ]

      )

    })

  }



  private getParams (params : ParamMap)
  {

    const id    = parseInt(params.get('id'))
    this.action = params.get('action')

    if ('create' === this.action) {
      this.createForm()
    }

  }



  private onCreate ()
  {

    this.afterSend()
    this.form.reset()

    this.hasNotice  = true
    this.message    = 'Dragão criado com sucesso'

  }



  private onSubmit ($event)
  {

    $event.preventDefault()
    $event.stopPropagation()

    this.beforeSend()

    const dragon      = new DragonModel()

    dragon.createdAt  = new Date().toDateString()
    dragon.headers    = {}
    dragon.histories  = []
    dragon.name       = this.name.value.trim()
    dragon.type       = this.type.value.trim()

    this.service
    .postDragon (dragon)
    .subscribe  (
      
      this.onCreate

    , (error)    => console.error(error)

    )

  }



  private get action ()
  {
    return (this._action)
  }

  private get form ()
  {
    return (this._form)
  }

  private get name ()
  {
    return (this.form.get('name'))
  }

  private get type ()
  {
    return (this.form.get('type'))
  }




  private set action (action)
  {
    this._action  = action
  }

  private set form (form)
  {
    this._form  = form
  }

}
