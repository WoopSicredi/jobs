
import { BrowserModule }          from '@angular/platform-browser'
import { LOCALE_ID, NgModule }    from '@angular/core'

import { DragonAppCommonModule }  from  './dragon-app-common/dragon-app-common.modules'
import { DragonModule }           from  './dragon/dragon.modules'
import { HomeModule }             from  './home/home.modules'
import { LoginModule }            from  './login/login.modules'
import { NotFoundModule }         from  './not-found/not-found.modules'

import { AppComponent }           from './app.component'
import { AppRoutingModule }       from './app-routing.module'





@NgModule({

  declarations: [
    AppComponent
  ]

, imports: [
    BrowserModule
  , DragonModule
  , DragonAppCommonModule
  , HomeModule
  , LoginModule
  , NotFoundModule
  , AppRoutingModule
  ]

, providers: [
    {
      provide:  LOCALE_ID
    , useValue: 'pt-BR'
    }
  ]
  
, bootstrap: [
    AppComponent
  ]

})
export class AppModule 
{ 

}
