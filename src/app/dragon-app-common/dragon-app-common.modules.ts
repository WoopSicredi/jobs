
import { ErrorHandler }     from '@angular/core'
import { HttpModule }       from '@angular/http'
import { NgModule }         from '@angular/core'

import { AppErrorHandler }          from './handlers/app-error-handler/app-error-handler'
import { AuthService }              from './auth/services/auth.service'
import { DataService }              from './services/data/data.service'
import { LoggedInPageComponent }    from './pages/logged-in-page/logged-in-page.component'
import { PageComponent }            from './pages/page/page.component'





@NgModule({

    declarations: [
        LoggedInPageComponent
    ,   PageComponent
    ]

    , imports: [
        HttpModule
    ]

    , providers: [
        AuthService
    ,   DataService
    ,   { provide: ErrorHandler, useClass: AppErrorHandler }
    ]
    
    , bootstrap: []

})
export class DragonAppCommonModule 
{ 

}
