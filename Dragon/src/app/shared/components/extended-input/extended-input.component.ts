import { Component, Input, OnChanges, OnInit } from "@angular/core";

@Component({
  selector: "extended-input",
  templateUrl: "./extended-input.component.html",
  styleUrls: ["./extended-input.component.scss"],
})
export class ExtendedInputComponent implements OnChanges {
  @Input()
  labelText: string = "";
  @Input()
  inputErrors: any;
  @Input()
  errorDefs: any;
  @Input()
  isError: boolean = false;

  errorMessage: string = "";

  ngOnChanges(changes: any): void {
    var errors: any = changes.inputErrors?.currentValue;
    this.errorMessage = "";
    if (errors) {
      Object.keys(this.errorDefs).some((key) => {
        if (errors[key]) {
          this.errorMessage = this.errorDefs[key];
          return true;
        }
      });
    }
  }
}
