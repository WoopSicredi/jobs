
import { NgModule }       from '@angular/core'
import { CommonModule }   from '@angular/common'
import { FormsModule }    from '@angular/forms'
 
import { NotFoundComponent }        from './pages/not-found/not-found.component'
import { NotFoundRoutingModule }    from './not-found-routing.modules'





@NgModule({

    imports: [
        CommonModule
    ,   FormsModule
    ,   NotFoundRoutingModule
    ]

,   declarations: [
        NotFoundComponent
    ]

})
export class NotFoundModule 
{

}
