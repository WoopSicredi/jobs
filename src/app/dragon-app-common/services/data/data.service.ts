import { Http }       from '@angular/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs/Observable'

import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch'
import 'rxjs/add/operator/map'

import { AppError }         from '../../errors/app-error'
import { BadRequestError }  from './../../errors/bad-request-error'
import { NotFoundError }    from './../../errors/not-found-error'





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



  public delete (id : number) : Observable<any | AppError>
  {

    const url   = `${this.url}/${id}`

    console.debug(url)

    return (

      this.http
      .delete (url)
      .map    ( (response) => response.json() )
      .catch  (this.onError)

    )

  }



  public get (id : number)  : Observable<any | AppError>
  {

    const url   = `${this.url}/${id}`

    return (

      this.http
      .get  (url)
      .map  ( (response) => response.json() )
      .catch(this.onError)

    )

  }


  public getAll ()  : Observable<any | AppError>
  {

    return (

      this.http
      .get  (this.url)
      .map  ( (response) => response.json() )
      .catch(this.onError)

    )

  }



  protected onError (originalError : Response)  : Observable<AppError>
  {

    if (400 === originalError.status) {
      return (Observable.throw(new BadRequestError(originalError)))
    }

    if (404 === originalError.status) {
      return (Observable.throw(new NotFoundError(originalError)))
    }

    return (Observable.throw(new AppError(originalError)))

  }



  public post (resource : object)
  {
    
    return (
      
      this.http
      .post( this.url, JSON.stringify(resource) )
      .map( (response) => response.json() )
      .catch(this.onError)

    )

  }



  public put (id : number, resource : object)
  {

    const url   = `${this.url}/${id}`

    return (
      this.http
      .put( url, JSON.stringify(resource) )
      .map( (response) => response.json() )
      .catch(this.onError)
      
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
