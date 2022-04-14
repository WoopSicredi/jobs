import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { Dragon } from 'src/app/shared/models';
import { DragonService } from '../../services/dragon.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  id: number;
  dragon: Dragon;
  constructor(private route: ActivatedRoute, private dragonService: DragonService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    this.dragonService.getDragon(this.id).subscribe((dragon: Dragon) => {
      this.dragon = dragon;
    });
  }

}
