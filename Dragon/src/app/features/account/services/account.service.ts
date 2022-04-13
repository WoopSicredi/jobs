import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, delay, Observable, of } from "rxjs";

import { User } from "src/app/shared/models/User";
import { LoginRequest } from "src/app/features/account/pages/login/login.model";

@Injectable({ providedIn: "root" })
export class AccountService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(private router: Router) {
    this.userSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(loginRequest: LoginRequest): Observable<LoginRequest> {
    return of(loginRequest).pipe(delay(2000));
  }

  logout() {
    localStorage.removeItem("currentUser");
    this.userSubject.next(null);
    this.router.navigate(["/account/login"]);
  }
}
