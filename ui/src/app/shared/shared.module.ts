import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {ShowLoggedInDirective} from './show-logged-in.directive';
import {
  MzButtonModule,
  MzCardModule,
  MzDropdownModule, MzIconModule,
  MzInputModule,
  MzSelectModule,
  MzSpinnerModule
} from "ngx-materialize";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    MzInputModule,
    MzButtonModule,
    MzCardModule,
    MzSelectModule,
    MzDropdownModule,
    MzSpinnerModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    MzInputModule,
    MzButtonModule,
    MzCardModule,
    MzSelectModule,
    MzDropdownModule,
    MzSpinnerModule,
    MzIconModule,
    ShowLoggedInDirective
  ],
  declarations: [ShowLoggedInDirective],
})
export class SharedModule {
}
