
import { Component, OnInit }  from '@angular/core'

import { DragonModel }        from '../../models/dragon.model';
import { DragonService }      from '../../services/dragon.service';





@Component({
  selector:     'dragon-dragon-list'
, templateUrl:  './dragon-list.component.html'
, styleUrls:    ['./dragon-list.component.scss']
})
export class DragonListComponent implements OnInit 
{

  private _dragons  : DragonModel[]
  private _service  : DragonService



  constructor (service : DragonService) 
  { 

    this.service  = service

  }



  ngOnInit () 
  {

    this.service
    .getDragons()
    .subscribe( (dragons) => this.dragons  = dragons )

  }



  public get dragons ()
  {
    return (this._dragons)
  }

  private get service ()
  {
    return (this._service)
  }



  public set dragons (dragons)
  {
    this._dragons = dragons
  }

  private set service (service)
  {
    this._service = service
  }

}
