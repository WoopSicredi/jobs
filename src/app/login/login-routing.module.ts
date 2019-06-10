
import { NgModule }             from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
 
import { LoginPageComponent }   from './pages/login-page/login-page.component'





const loginRoutes : Routes = [
    { path: 'login',    component: LoginPageComponent }
]



@NgModule({

    imports: [
        RouterModule.forChild(loginRoutes)
    ]

,   exports: [
        RouterModule
    ]
    
})
export class LoginRoutingModule 
{ 

}
