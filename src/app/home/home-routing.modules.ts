
import { NgModule }             from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
 
import { HomePageComponent }    from './pages/home-page/home-page.component'





const homeRoutes : Routes = [
    { path: '',         component: HomePageComponent }
,   { path: 'home',     component: HomePageComponent }
,   { path: 'index',    component: HomePageComponent }
]



@NgModule({

    imports: [
        RouterModule.forChild(homeRoutes)
    ]

,   exports: [
        RouterModule
    ]
    
})
export class HomeRoutingModule 
{ 

}
