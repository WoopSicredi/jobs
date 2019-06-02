
import { Component, OnInit }      from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'





@Component({
  selector:     'dragon-page'
, templateUrl:  './page.component.html'
, styleUrls:    ['./page.component.scss']
})
export class PageComponent implements OnInit 
{

  protected _route  : ActivatedRoute
  protected _router : Router



  constructor (
    route   : ActivatedRoute
  , router  : Router
  ) 
  { 

    this.route  = route
    this.router = router

  }



  ngOnInit () 
  {

  }



  protected get route ()
  {
    return (this._route)
  }

  protected get router ()
  {
    return (this._router)
  }



  protected set route (route)
  {
    this._route  = route
  }

  protected set router (router)
  {
    this._router  = router
  }

}
