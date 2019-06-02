
import { Http }         from '@angular/http'
import { Injectable }   from '@angular/core'
import { Observable }   from 'rxjs/Observable'

import 'rxjs/add/operator/switchMap'

import { environment }  from './../../../environments/environment'
import { DataService }  from './../../dragon-app-common/services/data/data.service'
import { DragonModel }  from '../models/dragon.model'




@Injectable()
export class DragonService extends DataService
{

  constructor (http: Http) 
  { 
    super((<string> environment.DRAGON_API_URL), http)
  }



  public getDragons ()  : Observable<DragonModel[]>
  {

    return (

      this
      .getAll()
      .map( (json) => Array.prototype.map.call(json, (tuple) => new DragonModel().deserialize(tuple) ) )
      .catch(this.onError)

    )

  }

}
