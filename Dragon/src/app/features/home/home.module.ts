import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DefaultComponent } from "./pages/default/default.component";
import { RouterModule } from "@angular/router";

const routes = [{ path: "", component: DefaultComponent }];

@NgModule({
  declarations: [DefaultComponent],
  imports: [CommonModule, RouterModule.forChild(routes)],
})
export class HomeModule {}
