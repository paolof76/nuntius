import {NgModule} from '@angular/core';
import {AccountRoutingModule} from "./account-routing.module";
import {AccountComponent} from "./account.component";
import {CommonModule} from "@angular/common";

@NgModule({
  imports: [
    CommonModule,
    AccountRoutingModule,
  ],
  declarations: [
    AccountComponent
  ]
})
export class AccountModule {
}
