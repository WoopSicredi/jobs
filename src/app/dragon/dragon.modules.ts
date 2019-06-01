
import { NgModule }       from '@angular/core'
import { CommonModule }   from '@angular/common'
import { FormsModule }    from '@angular/forms'
 
import { DragonFormComponent }      from './pages/dragon-form/dragon-form.component'
import { DragonListComponent }      from './pages/dragon-list/dragon-list.component'
import { DragonProfileComponent }   from './pages/dragon-profile/dragon-profile.component'
import { DragonRoutingModule }      from './dragon-routing.module'
import { DragonService }            from './services/dragon.service'





@NgModule({

    imports: [
        CommonModule
    ,   FormsModule
    ,   DragonRoutingModule
    ]

,   declarations: [
        DragonFormComponent
    ,   DragonListComponent
    ,   DragonProfileComponent
    ]

,   providers: [
        DragonService
    ]

})
export class DragonModule 
{

}
