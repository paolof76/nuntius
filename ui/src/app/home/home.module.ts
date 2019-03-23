import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import {SharedModule} from '../shared';
import { HomeRoutingModule } from './home-routing.module';
import {CoreModule} from "../core";

@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    SharedModule,
    HomeRoutingModule,
    CoreModule
  ]
})
export class HomeModule { }
