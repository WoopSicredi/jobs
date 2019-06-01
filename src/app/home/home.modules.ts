
import { NgModule }       from '@angular/core'
import { CommonModule }   from '@angular/common'
 
import { HomePageComponent }    from './pages/home-page/home-page.component'
import { HomeRoutingModule }    from './home-routing.modules'





@NgModule({

    imports: [
        CommonModule
    ,   HomeRoutingModule
    ]

,   declarations: [
        HomePageComponent
    ]

})
export class HomeModule
{

}
