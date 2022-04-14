import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Dragon } from 'src/app/shared/models/Dragon';

@Injectable({ providedIn: 'root' })
export class DragonService {
  constructor(private httpClient: HttpClient) {}

  getDragons() {
    return this.httpClient.get(
      'http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon'
    );
  }

  getDragon(id: number) {
    return this.httpClient.get(
      `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${id}`
    );
  }

  removeDragon(id: number) {
    return this.httpClient.delete(
      `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${id}`
    );
  }

  createDragon(dragon: Dragon) {
    return this.httpClient.post(
      'http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon',
      dragon
    );
  }

  updateDragon(dragon: Dragon) {
    return this.httpClient.put(
      `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${dragon.id}`,
      dragon
    );
  }
}
