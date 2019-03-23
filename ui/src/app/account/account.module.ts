import {NgModule} from '@angular/core';
import {AccountRoutingModule} from "./account-routing.module";
import {AccountComponent} from "./account.component";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared";

@NgModule({
  declarations: [AccountComponent],
  imports: [
    CommonModule,
    SharedModule,
    AccountRoutingModule
  ]
})
export class AccountModule {
}
