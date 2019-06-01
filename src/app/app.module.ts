import { BrowserModule }                    from '@angular/platform-browser'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpModule }                       from '@angular/http'
import { NgModule }                         from '@angular/core'

import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/common/home/home-page/home-page.component';
import { PageComponent } from './pages/common/page/page.component';
import { LoginPageComponent } from './pages/login/login-page/login-page.component'



@NgModule({

  declarations: [
    AppComponent,
    HomePageComponent,
    PageComponent,
    LoginPageComponent
  ]

, imports: [
    BrowserModule
  , FormsModule
  , HttpModule
  , ReactiveFormsModule
  ]

, providers: []
  
, bootstrap: [
    AppComponent
  ]

})

export class AppModule { }
