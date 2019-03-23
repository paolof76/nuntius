import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AccountComponent} from "./account.component";
import {AccountAddComponent} from "./account-add.component";

const routes: Routes = [
  {
    path: '',
    component: AccountComponent,
  },
  {
    path: 'add',
    component: AccountAddComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule {}
