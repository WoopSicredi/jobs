import { Component, Input, OnChanges, SimpleChanges } from "@angular/core";
import { FormControl } from "@angular/forms";

@Component({
  selector: "show-errors",
  templateUrl: "./show-errors.component.html",
  styleUrls: ["./show-errors.component.scss"],
})
export class ShowErrorsComponent {
  private static readonly errorMessages = {
    required: () => "Este campo é obrigatório.",
    email: () => "O e-mail informado é inválido.",
    minlength: (params: any): string =>
      "O número mínimo de caracteres é " + params.requiredLength,
    maxlength: (params: any): string =>
      "O número máximo permitido de caracteres é " + params.requiredLength,
    pattern: (params: any): string =>
      "O padrão necessário é: " + params.requiredPattern,
    years: (params: any): string => params.message,
    countryCity: (params: any): string => params.message,
    uniqueName: (params: any): string => params.message,
    telephoneNumbers: (params: any): string => params.message,
    telephoneNumber: (params: any): string => params.message,
  };

  @Input()
  private field: FormControl;

  shouldShowErrors(): boolean {
    return (
      this.field &&
      this.field.errors &&
      (this.field.dirty || this.field.touched)
    );
  }

  listOfErrors(): string[] {
    return Object.keys(this.field.errors).map((error: string) =>
      this.getMessage(error)
    );
  }

  private getMessage(error: string) {
    return ShowErrorsComponent.errorMessages[error]();
  }
}
