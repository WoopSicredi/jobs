
import { NgModule }                 from '@angular/core'
import { RouterModule, Routes }     from '@angular/router'
 
import { DragonFormComponent }      from './pages/dragon-form/dragon-form.component'
import { DragonListComponent }      from './pages/dragon-list/dragon-list.component'
import { DragonProfileComponent }   from './pages/dragon-profile/dragon-profile.component'





const dragonRoutes : Routes = [
    { path: 'dragon/:id/:nome/:action',     component: DragonFormComponent    }
,   { path: 'dragons/:id/:nome/:action',    component: DragonFormComponent    }
,   { path: 'dragon/:id//:action',          component: DragonFormComponent    }
,   { path: 'dragons/:id//:action',         component: DragonFormComponent    }
,   { path: 'dragon/:id/:nome',             component: DragonProfileComponent }
,   { path: 'dragons/:id/:nome',            component: DragonProfileComponent }
,   { path: 'dragon/:id',                   component: DragonProfileComponent }
,   { path: 'dragons/:id',                  component: DragonProfileComponent }
,   { path: 'dragons',                      component: DragonListComponent    }
,   { path: 'dragon',                       component: DragonListComponent    }
]
 


@NgModule({

    imports: [
        RouterModule.forChild(dragonRoutes)
    ]

,   exports: [
        RouterModule
    ]
    
})
export class DragonRoutingModule 
{ 

}
