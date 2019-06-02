
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Component, OnInit }                from '@angular/core'

import { AuthService }            from '../../../dragon-app-common/auth/services/auth.service'
import { DragonModel }            from '../../models/dragon.model'
import { DragonPageComponent }    from '../dragon-page/dragon-page.component'
import { DragonService }          from '../../services/dragon.service'





@Component({
  selector:     'dragon-dragon-profile'
, templateUrl:  './dragon-profile.component.html'
, styleUrls:    ['./dragon-profile.component.scss']
})
export class DragonProfileComponent extends DragonPageComponent implements OnInit 
{

  constructor (
    service : DragonService
  , auth    : AuthService
  , route   : ActivatedRoute
  , router  : Router
  )
  { 

    super(service, auth, route, router)

    this.getDragon  = this.getDragon.bind(this)
    this.onError    = this.onError.bind(this)
    this.onGet      = this.onGet.bind(this)

  }



  ngOnInit () 
  {

    this.route
    .paramMap
    .subscribe( this.getDragon )

  }



  private getDragon (params : ParamMap)
  {

    this.service
    .getDragon(parseInt(params.get('id')))
    .subscribe(
      this.onGet
    , this.onError
    )

  }



  private onError (error)
  {

    this.router.navigate(['/not-found'])

  }




  private onGet (dragon : DragonModel)
  {

    this.onDismissToast()
    this.dragon = dragon

  }

}
