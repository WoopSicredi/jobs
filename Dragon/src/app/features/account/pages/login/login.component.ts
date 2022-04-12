import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup | any;
  submitted: boolean = false;
  invalidLogin: boolean = false;
  constructor(private formBuilder: FormBuilder, private router: Router) {}

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    if (
      this.loginForm.controls.email.value == "marcosmessiasdev@gmail.com" &&
      this.loginForm.controls.password.value == "password"
    ) {
      this.router.navigate(["/"]);
    } else {
      this.invalidLogin = true;
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }
}
