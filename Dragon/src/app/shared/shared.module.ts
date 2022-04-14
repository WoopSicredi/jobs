import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ExtendedInputComponent } from './components/extended-input/extended-input.component';
import { ShowErrorsComponent } from './components/show-errors/show-errors.component';
import { AccountLayoutComponent } from './layouts/account-layout/account-layout.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { SystemLayoutComponent } from './layouts/system-layout/system-layout.component';
import { ModalComponent } from './components/modal/modal.component';

@NgModule({
  declarations: [
    ExtendedInputComponent,
    ShowErrorsComponent,
    NavbarComponent,
    SidebarComponent,
    AccountLayoutComponent,
    SystemLayoutComponent,
    ModalComponent,
  ],
  imports: [CommonModule, RouterModule],
  exports: [ExtendedInputComponent, ShowErrorsComponent, ModalComponent],
})
export class SharedModule {}
