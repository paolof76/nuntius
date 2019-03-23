import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {ShowLoggedInDirective} from './show-logged-in.directive';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MzButtonModule, MzDropdownModule} from 'ngx-materialize';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    ShowLoggedInDirective,
    BrowserAnimationsModule,
    MzDropdownModule,
    MzButtonModule
  ],
  declarations: [ShowLoggedInDirective],
})
export class SharedModule {
}
