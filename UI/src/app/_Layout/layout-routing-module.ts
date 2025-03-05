import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { HomeComponent } from './home/home.component'
import { SideNavComponent } from './sidenav/sidenav.component'
import { HeaderComponent } from './header/header.component'

const routes:Routes=[
    {
        path:'',
        redirectTo:'home',
        pathMatch:'full'
    },
    {
        path:'home',
        component:HomeComponent
    },
    {
        path:'sidenav',
        component:SideNavComponent
    },
    {
        path:'header',
        component:HeaderComponent
    }
]

@NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
})
export class LayoutRoutingModule {}