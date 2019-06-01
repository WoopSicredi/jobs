
import { NgModule }       from '@angular/core'
import { CommonModule }   from '@angular/common'
import { FormsModule }    from '@angular/forms'
 
import { LoginPageComponent }   from './pages/login-page/login-page.component'
import { LoginRoutingModule }   from './login-routing.module'





@NgModule({

    imports: [
        CommonModule
    ,   FormsModule
    ,   LoginRoutingModule
    ]

,   declarations: [
        LoginPageComponent
    ]

})
export class LoginModule 
{

}
