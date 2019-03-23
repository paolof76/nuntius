import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {HttpTokenInterceptor} from './interceptors';
import {
  AccountService,
  UserService
} from './services';
import {ApiService} from './services/api.service';

@NgModule({
  declarations: [],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true },
    ApiService,
    UserService,
    AccountService
  ],
  imports: [
    CommonModule
  ]
})
export class CoreModule { }
