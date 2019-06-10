
import { NgModule }                         from '@angular/core'
import { CommonModule }                     from '@angular/common'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
 
import { DragonFormComponent }      from './pages/dragon-form/dragon-form.component'
import { DragonListComponent }      from './pages/dragon-list/dragon-list.component'
import { DragonPageComponent }      from './pages/dragon-page/dragon-page.component'
import { DragonProfileComponent }   from './pages/dragon-profile/dragon-profile.component'
import { DragonRoutingModule }      from './dragon-routing.module'
import { DragonService }            from './services/dragon.service'





@NgModule({

    imports: [
        CommonModule
    ,   DragonRoutingModule
    ,   FormsModule
    ,   ReactiveFormsModule
    ]

,   declarations: [
        DragonFormComponent
    ,   DragonListComponent
    ,   DragonPageComponent
    ,   DragonProfileComponent
    ]

,   providers: [
        DragonService
    ]

})
export class DragonModule 
{

}
