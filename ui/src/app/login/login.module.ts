import {NgModule} from '@angular/core';

import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {NoLoginGuard} from './no-login-guard.service';
import {SharedModule} from '../shared';

@NgModule({
  declarations: [LoginComponent],
  imports: [
    SharedModule,
    LoginRoutingModule
  ],
  providers: [
    NoLoginGuard
  ]
})
export class LoginModule {
}
