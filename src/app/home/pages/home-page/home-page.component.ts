
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit }      from '@angular/core'

import { AuthService }            from './../../../dragon-app-common/auth/services/auth.service'
import { LoggedInPageComponent }  from './../../../dragon-app-common/pages/logged-in-page/logged-in-page.component'






@Component({
  selector:     'dragon-home-page'
, templateUrl:  './home-page.component.html'
, styleUrls:    ['./home-page.component.scss']
})
export class HomePageComponent extends LoggedInPageComponent implements OnInit 
{

  constructor (
    auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  ) 
  {
    super(auth, route, router)
  }
    

  ngOnInit () 
  {
    this.router.navigate(['/dragons'])
  }

}
