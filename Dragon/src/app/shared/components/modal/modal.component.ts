import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit, OnChanges {
  @Input() show = false;
  @Input() modalData: object = {};
  @Input() image = '';

  @Output() close = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  onClose() {
    this.close.emit(!this.show);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.show) {
      this.show = changes.show.currentValue;
    }
    if (changes.modalData) {
      this.modalData = changes.modalData.currentValue;
    }
    if (changes.image) {
      this.image = changes.image.currentValue;
    }
  }
}
