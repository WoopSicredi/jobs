import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./pages/login/login.component";
import { RegisterComponent } from "./pages/register/register.component";
import { RouterModule } from "@angular/router";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ExtendedInputComponent } from "src/app/shared/components/extended-input/extended-input.component";

const routes = [{ path: "login", component: LoginComponent }];

@NgModule({
  declarations: [LoginComponent, RegisterComponent, ExtendedInputComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
  ],
})
export class AccountModule {}
