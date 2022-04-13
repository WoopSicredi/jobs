import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { SidebarComponent } from "./components/sidebar/sidebar.component";
import { AccountLayoutComponent } from "./layouts/account-layout/account-layout.component";
import { SystemLayoutComponent } from "./layouts/system-layout/system-layout.component";
import { RouterModule } from "@angular/router";

@NgModule({
  declarations: [
    NavbarComponent,
    SidebarComponent,
    AccountLayoutComponent,
    SystemLayoutComponent,
  ],
  imports: [CommonModule, RouterModule],
})
export class CoreModule {}
