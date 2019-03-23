import {Component, OnInit} from '@angular/core';
import {User, Account} from "../core/models";
import {UserService} from "../core/services";
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
    this.userService.currentUser.subscribe(
      (userData: User) => {
        this.currentUser = userData;
      }
    );

    this.accounts.push({id:1,institution:'UBX',iban:'CH34 0500 0000 1234 2324 6',amount:10000,currency:'CHF',interestRate:'0%'} as Account);
    this.accounts.push({id:2,institution:'SCC',iban:'CH34 0500 0000 1234 5656 6',amount:20000,currency:'CHF',interestRate:'0%'} as Account);
    this.accounts.push({id:3,institution:'SER',iban:'CH34 0500 0000 1234 3434 6',amount:3000,currency:'EUR',interestRate:'0%'} as Account);
    this.accounts.push({id:4,institution:'AAA',iban:'CH34 0500 0000 1234 8989 6',amount:5000,currency:'USD',interestRate:'0.5%'} as Account);
  }

  submitForm() {
    console.log(this.accountForm.value)
  }
}
