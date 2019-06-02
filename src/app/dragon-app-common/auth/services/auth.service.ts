
import { Injectable } from '@angular/core'

import { environment }  from  './../../../../environments/environment'  

import { LoginInfoModel } from './../models/login-info.model'





@Injectable()
export class AuthService 
{

  public static _isLogedIn  : bool



  private constructor () 
  { 
    //  Apenas métodos estáticos aqui
  }



  public static logIn (loginInfo : LoginInfoModel)  : boolean
  {
    
    const loginShouldBe     = (<string> environment.TEST_USERNAME)
    const passwordShouldBe  = (<string> environment.TEST_USERNAME)

    if (0 != (loginShouldBe.localeCompare(loginInfo.username))) {
      return (false)
    }

    if (0 != (passwordShouldBe.localeCompare(loginInfo.password))) {
      return (false)
    }

    return (true)

  }



  public static logOut ()
  {
    this._isLogedIn = false
  }



  public static get isLogedIn ()
  {
    return (this._isLogedIn)
  }

}
