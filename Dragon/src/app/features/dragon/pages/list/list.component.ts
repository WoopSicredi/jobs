import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Dragon } from 'src/app/shared/models/Dragon';
import { DragonService } from '../../services/dragon.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  dragons: Dragon[] = [];
  showModal = false;
  modalData = {};

  constructor(
    private dragonService: DragonService,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {
    this.list();
  }

  showDragonModal(id: number): void {
    this.showModal = true;
  }

  statusModal(show: boolean) {
    this.showModal = show;
  }

  list() {
    this.dragonService.getDragons().subscribe((dragons: Dragon[]) => {
      this.dragons = dragons.sort((a, b) => {
        if (a.name > b.name) {
          return 1;
        }
        if (a.name < b.name) {
          return -1;
        }
        return 0;
      });
    });
  }

  removeDragon(id: number, event: Event) {
    event.preventDefault();
    this.dragonService.removeDragon(id).subscribe({
      next: (v) => console.log(v),
      error: (e) => {
        this.toastrService.error(e.error.message);
      },
      complete: () => {
        this.toastrService.success('Dragão removido com sucesso');
        this.list();
      },
    });
  }
}
