import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {NoLoginGuard} from './no-login-guard.service';
import {MzButtonModule, MzInputModule} from 'ngx-materialize';
import {SharedModule} from '../shared';

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    SharedModule,
    LoginRoutingModule,
    MzInputModule,
    MzButtonModule
  ],
  providers: [
    NoLoginGuard
  ]
})
export class LoginModule {
}
