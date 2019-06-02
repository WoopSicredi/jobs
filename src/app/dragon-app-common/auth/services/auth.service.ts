
import { Injectable } from '@angular/core'

import { environment }  from  './../../../../environments/environment'  

import { LoginError }     from '../errors/login-error'
import { LoginInfoModel } from './../models/login-info.model'





@Injectable()
export class AuthService 
{

  public static _isLoggedIn  : boolean  = false



  public constructor () 
  { 
    //  Apenas métodos estáticos aqui
  }



  //  Apenas mockeando um login assíncrono
  public logIn (loginInfo : LoginInfoModel)  : Promise<boolean | LoginError>
  {

    
    if (this.isLoggedIn) {

      return ( new Promise ( (resolve) => resolve(true) ) )

    }


    //  Caso não esteja logado
    return (

      new Promise ((resolve, reject) => 
      {

        setTimeout(() =>
        {

          const loginShouldBe     = (<string> environment.TEST_USERNAME)
          const passwordShouldBe  = (<string> environment.TEST_USERNAME)

          if (0 != (loginShouldBe.localeCompare(loginInfo.username))) {
            return(reject(new LoginError({ invalidUsername: true })))
          }

          if (0 != (passwordShouldBe.localeCompare(loginInfo.password))) {
            return(reject(new LoginError( { invalidPassword: true } )))
          }

          AuthService._isLoggedIn = true
          resolve (true)

        }, 333)

      })

    )

  }



  public logOut ()  : void
  {
    AuthService._isLoggedIn = false
  }



  public get isLoggedIn () : boolean
  {
    return (AuthService._isLoggedIn)
  }

}
