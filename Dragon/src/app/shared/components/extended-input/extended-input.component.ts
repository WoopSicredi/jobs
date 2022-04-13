import { Component, Input, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";

@Component({
  selector: "extended-input",
  templateUrl: "./extended-input.component.html",
  styleUrls: ["./extended-input.component.scss"],
})
export class ExtendedInputComponent {
  @Input()
  labelText: string = "";
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
