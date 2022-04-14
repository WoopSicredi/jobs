import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "./shared/guards/auth.guard";
import { AccountLayoutComponent } from "./shared/layouts/account-layout/account-layout.component";
import { SystemLayoutComponent } from "./shared/layouts/system-layout/system-layout.component";

const homeModule = () =>
  import("./features/home/home.module").then((x) => x.HomeModule);
const userModule = () =>
  import("./features/user/user.module").then((x) => x.UserModule);
const accountModule = () =>
  import("./features/account/account.module").then((x) => x.AccountModule);
const dragonModule = () =>
  import("./features/dragon/dragon.module").then((x) => x.DragonModule);

const routes: Routes = [
  {
    path: "account",
    component: AccountLayoutComponent,
    loadChildren: accountModule,
  },
  {
    path: "",
    component: SystemLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: "", loadChildren: homeModule },
      { path: "user", loadChildren: userModule },
      { path: "dragon", loadChildren: dragonModule },
    ],
  },
  { path: "**", redirectTo: "" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
