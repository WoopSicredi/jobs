import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Dragon } from 'src/app/shared/models/Dragon';
import { DragonService } from '../../services/dragon.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
})
export class FormComponent implements OnInit {
  dragon: Dragon;
  dragonForm: FormGroup | any;
  id: number | any;
  title = 'Cadastro de Dragões';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private dragonService: DragonService,
    private toastr: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    if (this.id) {
      this.title = 'Edição de Dragões';
      this.getDragon(this.id);
    }

    this.dragonForm = this.formBuilder.group({
      name: [this.dragon ? this.dragon.name : '', [Validators.required]],
      type: [this.dragon ? this.dragon.type : '', [Validators.required]],
      id: [this.id ? this.id : null],
    });
  }

  getDragon(id: number) {
    this.dragonService.getDragon(id).subscribe((dragon: Dragon) => {
      this.dragon = dragon;
      this.f.name.setValue(dragon.name);
      this.f.type.setValue(dragon.type);
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
      id: this.f.id.value,
    };

    if (this.id) {
      this.dragonService.updateDragon(dragonRequest).subscribe({
        next: () => {
          this.toastr.success('Dragão atualizado com sucesso', 'Sucesso');
          this.router.navigate(['/dragon']);
        },
        error: (e) => {
          this.toastr.error(e.error.message, 'Erro');
        },
      });
    } else {
      dragonRequest.createdAt = new Date().toISOString();
      this.dragonService.createDragon(dragonRequest).subscribe({
        next: () => {
          this.toastr.success('Dragão cadastrado com sucesso', 'Sucesso');
          this.router.navigate(['/dragon']);
        },
        error: (e) => {
          this.toastr.error(e.error.message, 'Erro');
        },
      });
    }
  }
}
