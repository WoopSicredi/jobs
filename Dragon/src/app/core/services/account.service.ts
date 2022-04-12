import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

import { User } from "src/app/shared/models/User";

@Injectable({ providedIn: "root" })
export class AccountService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(username: string, password: string) {
    return this.http
      .post<User>(`/api/users/authenticate`, { username, password })
      .subscribe(
        (user) => {
          localStorage.setItem("currentUser", JSON.stringify(user));
          this.userSubject.next(user);
        },
        (error) => {
          console.log(error);
        }
      );
  }

  logout() {
    localStorage.removeItem("currentUser");
    this.userSubject.next(null);
    this.router.navigate(["/login"]);
  }
}
