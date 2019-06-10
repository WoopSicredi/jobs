
import { NgModule }       from '@angular/core'
import { CommonModule }   from '@angular/common'

import { DragonAppCommonModule } from '../dragon-app-common/dragon-app-common.modules';

import { HomePageComponent }    from './pages/home-page/home-page.component'
import { HomeRoutingModule }    from './home-routing.modules'






@NgModule({

    imports: [
        CommonModule
    ,   DragonAppCommonModule
    ,   HomeRoutingModule
    ]

,   declarations: [
        HomePageComponent
    ]

})
export class HomeModule
{

}
