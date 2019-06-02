import { Http }       from '@angular/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs/Observable'

import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch'
import 'rxjs/add/operator/map'





@Injectable()
export class DataService 
{

  protected _http : Http
  protected _url  : string



  constructor (url : string, http: Http) 
  { 

    this._http  =   http
    this._url   =   url

  }



  public getAll ()
  {

    return (

      this.http
      .get(this.url)
      .map( (response) => response.json() )
      .catch(this.onError)

    )

  }



  protected onError (originalError : Response)
  {

    return (
      Observable.throw(originalError)
    )

  }



  protected get http ()
  {
    return (this._http)
  }

  public get url ()
  {
    return (this._url)
  }

}
