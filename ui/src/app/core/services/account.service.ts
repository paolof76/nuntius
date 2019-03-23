import {Injectable} from '@angular/core';

import {ApiService} from './api.service';
import {Account} from "../models";
import {Observable} from "rxjs";

@Injectable()
export class AccountService {

  constructor(
    private apiService: ApiService
  ) {
  }

  addAccount(userId: number, institutionId: number): Observable<Account> {
    console.log('adding account, userid: ' + userId + ' instiId: ' + institutionId);
    return this.apiService.get('/account/create?client=' + userId + '&institution=' + institutionId);
  }

  getAccounts(userId): Observable<Account[]>{
    return this.apiService.get('/account/all?client=' + userId);

  }
}
