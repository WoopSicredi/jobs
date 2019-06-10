
import { NgModule }             from '@angular/core'
import { RouterModule, Routes } from '@angular/router'





const appRoutes: Routes = [
    { path: '',         redirectTo: '/home',        pathMatch: 'full'   } 
,   { path: 'home',     redirectTo: '/home',        pathMatch: 'full'   } 
,   { path: 'index',    redirectTo: '/home',        pathMatch: 'full'   } 
,   { path: 'dragon',   redirectTo: '/dragon',      pathMatch: 'prefix' }
,   { path: 'dragons',  redirectTo: '/dragon',      pathMatch: 'prefix' }
,   { path: 'login',    redirectTo: '/login',       pathMatch: 'prefix' }
,   { path: '**',       redirectTo: '/not-found',   pathMatch: 'full'   } 
]



@NgModule({

    imports: [
        RouterModule.forRoot(
            appRoutes
        )
    ]
    
,   exports: [
        RouterModule
    ]

})
export class AppRoutingModule 
{

}
