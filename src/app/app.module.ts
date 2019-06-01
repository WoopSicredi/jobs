
import { BrowserModule }                    from '@angular/platform-browser'
import { ErrorHandler }                     from '@angular/core'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpModule }                       from '@angular/http'
import { NgModule }                         from '@angular/core'

import { DragonModule }     from  './dragon/dragon.modules'
import { HomeModule }       from  './home/home.modules'
import { LoginModule }      from  './login/login.modules'
import { NotFoundModule }   from  './not-found/not-found.modules'

import { AppComponent }     from './app.component'
import { AppErrorHandler }  from './common/errors/app-error-handler/app-error-handler'
import { AppRoutingModule } from './app-routing.module'
import { AuthService }      from './common/auth/services/auth.service'
import { DataService }      from './common/services/data/data.service'
import { PageComponent }    from './common/page/pages/page.component'





@NgModule({

  declarations: [
    AppComponent
  , PageComponent
  ]

, imports: [
    BrowserModule
  , DragonModule
  , FormsModule
  , HomeModule
  , HttpModule
  , LoginModule
  , NotFoundModule
  , ReactiveFormsModule
  , AppRoutingModule
  ]

, providers: [
    AuthService
  , DataService
  , { provide: ErrorHandler, useClass: AppErrorHandler }
  ]
  
, bootstrap: [
    AppComponent
  ]

})
export class AppModule 
{ 

}
