import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'extended-input',
  templateUrl: './extended-input.component.html',
  styleUrls: ['./extended-input.component.scss'],
})
export class ExtendedInputComponent {
  @Input()
  labelText = '';
  @Input()
  field: FormControl;
  @Input()
  isError: boolean;

  shouldShowErrors(): boolean {
    return (
      this.field &&
      this.field.errors &&
      (this.field.dirty || this.field.touched)
    );
  }
}
