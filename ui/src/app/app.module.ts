import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {
  FooterComponent,
  HeaderComponent,
  SharedModule
} from './shared';
import {HomeModule} from './home/home.module';
import {CoreModule} from './core';
import {LoginModule} from './login/login.module';
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [AppComponent, FooterComponent, HeaderComponent],
  imports: [
    BrowserModule,
    CoreModule,
    SharedModule,
    HomeModule,
    LoginModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
