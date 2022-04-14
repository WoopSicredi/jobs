import { Injectable } from "@angular/core";
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from "@angular/router";

@Injectable({ providedIn: "root" })
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (localStorage.getItem("currentUser")) {
      return true;
    }
    if (state.url.length > 1) {
      this.router.navigate(["/account/login"], {
        queryParams: { returnUrl: state.url },
      });
    } else {
      this.router.navigate(["/account/login"]);
    }
    return false;
  }
}
