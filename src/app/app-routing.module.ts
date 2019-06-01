import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
 
import { DragonFormComponent }    from './pages/dragon/dragon-form/dragon-form.component'
import { DragonListComponent }    from './pages/dragon/dragon-list/dragon-list.component'
import { DragonProfileComponent } from './pages/dragon/dragon-profile/dragon-profile.component'
import { HomePageComponent }      from './pages/common/home/home-page/home-page.component'
import { LoginPageComponent }     from './pages/login/login-page/login-page.component'
import { NotFoundComponent }      from './pages/common/home/not-found/not-found.component'
 




const appRoutes: Routes = [
    { path: '',         component: HomePageComponent }
,   { path: 'dragon',   redirectTo: '/dragon',  pathMatch: 'prefix' }
,   { path: 'dragons',  redirectTo: '/dragon',  pathMatch: 'prefix' }
,   { path: '**',       component: NotFoundComponent }
]



@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes
        )
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule 
{

}
