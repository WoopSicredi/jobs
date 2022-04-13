import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { UserModule } from "./user/user.module";
import { HomeModule } from "./home/home.module";
import { AccountModule } from "./account/account.module";
import { DragonModule } from "./dragon/dragon.module";
import { ExtendedInputComponent } from "../shared/components/extended-input/extended-input.component";
import { ShowErrorsComponent } from "../shared/components/show-errors/show-errors.component";

@NgModule({
  declarations: [ExtendedInputComponent, ShowErrorsComponent],
  imports: [CommonModule, UserModule, HomeModule, AccountModule, DragonModule],
})
export class FeaturesModule {}
