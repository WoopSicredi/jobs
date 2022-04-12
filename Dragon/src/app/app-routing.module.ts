import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "./core/guards/auth.guard";

const homeModule = () =>
  import("./features/home/home.module").then((x) => x.HomeModule);
const userModule = () =>
  import("./features/user/user.module").then((x) => x.UserModule);
const accountModule = () =>
  import("./features/account/account.module").then((x) => x.AccountModule);
const dragonModule = () =>
  import("./features/dragon/dragon.module").then((x) => x.DragonModule);

const routes: Routes = [
  { path: "", loadChildren: homeModule, canActivate: [AuthGuard] },
  { path: "user", loadChildren: userModule, canActivate: [AuthGuard] },
  { path: "dragon", loadChildren: dragonModule, canActivate: [AuthGuard] },
  { path: "account", loadChildren: accountModule },
  { path: "**", redirectTo: "" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
