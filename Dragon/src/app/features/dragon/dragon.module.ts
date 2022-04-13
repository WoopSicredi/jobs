import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ListComponent } from "./pages/list/list.component";
import { RouterModule } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { FormComponent } from "./pages/form/form.component";
import { DetailComponent } from "./pages/detail/detail.component";
import { FormsModule } from "@angular/forms";

const routes = [
  { path: "", component: ListComponent },
  { path: "form", component: FormComponent },
  { path: "form/:id", component: FormComponent },
  { path: "detail/:id", component: DetailComponent },
];

@NgModule({
  declarations: [ListComponent, FormComponent, DetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forChild(routes),
  ],
})
export class DragonModule {}
