import {Component, OnInit} from '@angular/core';
import {User, Account} from "../core/models";
import {AccountService, UserService} from "../core/services";
import {ApiService} from "../core/services/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
})
export class AccountComponent implements OnInit {
  accounts: Account[];
  currentUser: User;
  accountForm: FormGroup;

  constructor(
    private userService: UserService,
    private accountService: AccountService,
    private apiService: ApiService,
    private fb: FormBuilder
  ) {
    this.accounts = new Array<Account>();
    this.accountForm = this.fb.group({
      institutionId: ['', Validators.required]
    });
  }

  ngOnInit() {
    // Load the current user's data
    console.log('userid:'+ this.userService.getCurrentUser().id);
    this.accountService.getAccounts(this.userService.getCurrentUser().id).subscribe(
      data => {
        console.log(data)
        this.accounts = data;
      }
    );
  }

  submitForm() {
    console.log(this.accountForm.value)
  }
}
