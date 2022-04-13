import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Dragon } from "src/app/shared/models/Dragon";
import { DragonService } from "../../services/dragon.service";

@Component({
  selector: "app-form",
  templateUrl: "./form.component.html",
  styleUrls: ["./form.component.scss"],
})
export class FormComponent implements OnInit {
  dragon: Dragon;
  dragonForm: FormGroup | any;
  id: number | any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private dragonService: DragonService,
    private toastr: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    this.dragonForm = this.formBuilder.group({
      name: ["", [Validators.required]],
      type: ["", [Validators.required]],
      id: [this.id ? this.id : null],
    });
  }

  get f() {
    return this.dragonForm.controls;
  }

  onSubmit() {
    if (this.dragonForm.invalid) {
      return;
    }

    const dragonRequest: Dragon = {
      name: this.f.name.value,
      type: this.f.type.value,
      histories: [],
      id: this.f.id.value,
      createdAt: new Date().toISOString(),
    };

    if (this.id) {
      this.dragonService.updateDragon(dragonRequest).subscribe({
        next: () => {
          this.toastr.success("Dragão atualizado com sucesso", "Sucesso");
          this.router.navigate(["/dragon/list"]);
        },
        error: (e) => {
          this.toastr.error(e.error.message, "Erro");
        },
      });
    } else {
      this.dragonService.createDragon(dragonRequest).subscribe({
        next: () => {
          this.toastr.success("Dragão cadastrado com sucesso", "Sucesso");
          this.router.navigate(["/dragon/list"]);
        },
        error: (e) => {
          this.toastr.error(e.error.message, "Erro");
        },
      });
    }
  }
}
