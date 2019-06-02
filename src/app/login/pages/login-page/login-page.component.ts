
import { ActivatedRoute, Router }             from '@angular/router'
import { Component, OnInit }                  from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'


import { AuthService }    from './../../../dragon-app-common/auth/services/auth.service'
import { LoginInfoModel } from './../../../dragon-app-common/auth/models/login-info.model'
import { LoginError }     from '../../../dragon-app-common/auth/errors/login-error'
import { PageComponent }  from '../../../dragon-app-common/pages/page/page.component';






@Component({
  selector:     'dragon-login-page'
, templateUrl:  './login-page.component.html'
, styleUrls:    ['./login-page.component.scss']
})
export class LoginPageComponent extends PageComponent implements OnInit 
{
  
  private _auth     : AuthService
  private _form     : FormGroup
  private _pending  : boolean =  false



  constructor (
    auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super (route, router)

    this.auth  = auth

    this.form = new FormGroup({

      password: new FormControl(

        ''

      , [ 
          Validators.required
        , Validators.minLength(6) 
        ]

      )

    , username: new FormControl(

        ''

      , [
          Validators.required
        , Validators.minLength(6) 
        ]

      )

    })

    this.afterSend  = this.afterSend.bind(this)
    this.beforeSend = this.beforeSend.bind(this)
    this.onError    = this.onError.bind(this)
    this.onSuccess  = this.onSuccess.bind(this)

  }



  ngOnInit () 
  {

    // if (this.auth.isLoggedIn) {
    //   this.router.navigate([''])
    // }

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



  private onError (error : LoginError)
  {

    this.afterSend()
    this.form.setErrors(error.errors) 

  }



  private onSubmit ($event)
  {

    $event.preventDefault()
    $event.stopPropagation()

    this.beforeSend()

    this.auth
    .logIn( new LoginInfoModel().deserialize(this.form.value) )
    .then ( this.onSuccess )
    .catch( this.onError )

  }



  private onSuccess (response)
  {
    
    this.afterSend()
    this.router.navigate([''])

  }



  private get auth ()
  {
    return (this._auth)
  }

  public get form ()
  {
    return (this._form)
  }

  private get password ()
  {
    return (this.form.get('password'))
  }

  private get pending ()
  {
    return (this._pending)
  }

  private get username ()
  {
    return (this.form.get('username'))
  }



  private set auth (auth)
  {
    this._auth  = auth
  }

  public set form (form)
  {
    this._form  = form
  }

  private set pending (pending)
  {
    this._pending  = pending
  }

}
