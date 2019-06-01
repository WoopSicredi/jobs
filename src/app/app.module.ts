import { BrowserModule }                    from '@angular/platform-browser'
import { ErrorHandler }                     from '@angular/core'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpModule }                       from '@angular/http'
import { NgModule }                         from '@angular/core'

import { AppComponent }           from './app.component'
import { AppErrorHandler }        from './common/error/app-error-handler'
import { AppRoutingModule }       from './app-routing.module';
import { AuthService }            from './services/auth/auth.service'
import { DataService }            from './services/common/data.service'
import { DragonFormComponent }    from './pages/dragon/dragon-form/dragon-form.component'
import { DragonListComponent }    from './pages/dragon/dragon-list/dragon-list.component'
import { DragonProfileComponent } from './pages/dragon/dragon-profile/dragon-profile.component'
import { DragonService }          from './services/dragon/dragon.service'
import { HomePageComponent }      from './pages/common/home/home-page/home-page.component'
import { LoginPageComponent }     from './pages/login/login-page/login-page.component'
import { NotFoundComponent }      from './pages/common/home/not-found/not-found.component'
import { PageComponent }          from './pages/common/page/page.component'





@NgModule({

  declarations: [
    AppComponent
  , DragonFormComponent
  , DragonListComponent
  , DragonProfileComponent
  , HomePageComponent
  , NotFoundComponent
  , PageComponent
  , LoginPageComponent
  ]

, imports: [

    BrowserModule
  , FormsModule
  , HttpModule
  , ReactiveFormsModule
  , AppRoutingModule

  ]

, providers: [
    AuthService
  , DataService
  , DragonService
  , { provide: ErrorHandler, useClass: AppErrorHandler }
  ]
  
, bootstrap: [
    AppComponent
  ]

})

export class AppModule { }
