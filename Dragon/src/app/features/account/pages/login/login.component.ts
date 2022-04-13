import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AccountService } from "src/app/features/account/services/account.service";
import { User } from "src/app/shared/models/User";
import { LoginRequest } from "./login.model";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup | any;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private accountService: AccountService,
    private toastr: ToastrService
  ) {}

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    const loginRequest: LoginRequest = {
      email: this.f.email.value,
      password: this.f.password.value,
    };

    this.accountService.login(loginRequest).subscribe((data: LoginRequest) => {
      if (
        data.email === "marcosmessiasdev@gmail.com" &&
        data.password === "123456"
      ) {
        const user: User = {
          id: 1,
          username: "marcosmessias",
          firstName: "Marcos",
          lastName: "Messias",
          email: "marcosmessiasdev@gmail.com",
        };
        localStorage.setItem("currentUser", JSON.stringify(user));
        this.router.navigate(["/"]);
      } else {
        this.toastr.error("Usuário ou senha inválidos", "Erro");
      }
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ["", [Validators.email, Validators.required]],
      password: ["", Validators.required],
    });
  }
}
