import { TestBed, inject } from '@angular/core/testing';

import { DragonsService } from './dragons.service';

describe('DragonsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DragonsService]
    });
  });

  it('should be created', inject([DragonsService], (service: DragonsService) => {
    expect(service).toBeTruthy();
  }));
});
