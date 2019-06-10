
import { Component, OnInit }      from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'

import { AuthService }    from '../../auth/services/auth.service';
import { PageComponent }  from '../page/page.component';





@Component({
  selector:     'logged-in-dragon-page'
, templateUrl:  './logged-in-page.component.html'
, styleUrls:    ['./logged-in-page.component.scss']
})
export class LoggedInPageComponent extends PageComponent implements OnInit 
{

  protected _auth   : AuthService
  protected _route  : ActivatedRoute
  protected _router : Router



  constructor (
    auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    super(route, router)
    
    this.auth = auth

    if (! this.auth.isLoggedIn)
    {
      this.router.navigate(['/login'])
    }  

  }



  ngOnInit () 
  {

    

  }



  protected get auth ()
  {
    return (this._auth)
  }



  protected set auth (auth)
  {
    this._auth  = auth
  }

}
