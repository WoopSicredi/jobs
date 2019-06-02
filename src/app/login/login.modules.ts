
import { NgModule }                         from '@angular/core'
import { CommonModule }                     from '@angular/common'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
 
import { LoginPageComponent }   from './pages/login-page/login-page.component'
import { LoginRoutingModule }   from './login-routing.module'





@NgModule({

    imports: [
        CommonModule
    ,   FormsModule
    ,   LoginRoutingModule
    ,   ReactiveFormsModule
    ]

,   declarations: [
        LoginPageComponent
    ]

})
export class LoginModule 
{

}
