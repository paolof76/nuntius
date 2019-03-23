import {Component, OnInit} from '@angular/core';
import {User, Account} from "../core/models";
import {UserService} from "../core/services";
import {ApiService} from "../core/services/api.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
})
export class AccountComponent implements OnInit {
  accounts: Account[];
  currentUser: User;

  constructor(
    private userService: UserService,
    private apiSerice: ApiService
  ) {
    this.accounts = new Array<Account>();
  }

  ngOnInit() {
    // Load the current user's data
    this.userService.currentUser.subscribe(
      (userData: User) => {
        this.currentUser = userData;
      }
    );

    this.accounts.push({id: 1} as Account);
  }

}
