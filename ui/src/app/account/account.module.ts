import {NgModule} from '@angular/core';
import {AccountRoutingModule} from "./account-routing.module";
import {AccountComponent} from "./account.component";
import {CommonModule} from "@angular/common";
import {MzCardModule} from "ngx-materialize";

@NgModule({
  imports: [
    CommonModule,
    AccountRoutingModule,
    MzCardModule
  ],
  declarations: [
    AccountComponent
  ]
})
export class AccountModule {
}
