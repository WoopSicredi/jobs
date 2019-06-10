
import { NgModule }             from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
 
import { NotFoundComponent }    from './pages/not-found/not-found.component'





const notFoundRoutes : Routes = [
  { path: '**', component: NotFoundComponent }
]



@NgModule({

    imports: [
        RouterModule.forChild(notFoundRoutes)
    ]

,   exports: [
        RouterModule
    ]
    
})
export class NotFoundRoutingModule 
{ 

}
