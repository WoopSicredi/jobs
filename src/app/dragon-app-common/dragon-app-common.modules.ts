
import { ErrorHandler }     from '@angular/core'
import { HttpModule }       from '@angular/http'
import { NgModule }         from '@angular/core'

import { AppErrorHandler }  from './errors/app-error-handler/app-error-handler'
import { AuthService }      from './auth/services/auth.service'
import { DataService }      from './services/data/data.service'
import { PageComponent }    from './page/pages/page.component'





@NgModule({

    declarations: [
        PageComponent
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
