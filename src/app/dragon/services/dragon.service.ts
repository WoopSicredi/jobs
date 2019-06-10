
import { Http }         from '@angular/http'
import { Injectable }   from '@angular/core'
import { Observable }   from 'rxjs/Observable'

import 'rxjs/add/operator/catch'
import 'rxjs/add/observable/throw'

import { environment }  from '../../../environments/environment'

import { AppError }             from '../../dragon-app-common/errors/app-error';
import { DataService }          from '../../dragon-app-common/services/data/data.service'
import { DragonError }          from '../errors/dragon-error'
import { DragonModel }          from '../models/dragon.model'
import { NotFoundError }        from '../../dragon-app-common/errors/not-found-error';
import { DragonNotFoundError }  from '../errors/dragon-not-found-error';





@Injectable()
export class DragonService extends DataService
{

  constructor (http: Http) 
  { 

    super((<string> environment.DRAGON_API_URL), http)

  }



  public deleteDragon (dragon : DragonModel) : Observable<DragonModel | DragonError>
  {

    const id : number  = dragon.id

    return (

      this
      .delete (id)
      .map    ( (json) => new DragonModel().deserialize(json) )
      .catch  ( this.onAppError )

    )

  }



  public getDragon (id : number)  : Observable<DragonModel | DragonError>
  {

    return (

      this
      .get(id)
      .map( (json) => new DragonModel().deserialize(json) )
      .catch ( this.onAppError )

    )

  }



  public getDragons ()  : Observable<DragonModel[] | DragonError>
  {

    return (

      this
      .getAll()
      .map( (json) => Array.prototype.map.call(json, (tuple) => new DragonModel().deserialize(tuple) ) )
      .catch ( this.onAppError )

    )

  }



  private onAppError (appError : AppError)
  {

    if (appError instanceof NotFoundError) {
      return (Observable.throw(new DragonNotFoundError({ dragonNotFound: true })))
    }

    return (Observable.throw(appError))

  }



  public postDragon (dragon : DragonModel)
  {
    
    return (
      
      this.post (dragon)
      .map      ( (json) => json )
      .catch    (this.onError)

    )

  }



  public putDragon (dragon : DragonModel)
  {

    const id : number  = dragon.id

    return (
      
      this.put  (id, dragon)
      .map      ( (json) => json )
      .catch    (this.onError)

    )

  }

}
