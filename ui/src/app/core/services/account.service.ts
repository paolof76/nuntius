import {Injectable} from '@angular/core';

import {ApiService} from './api.service';

@Injectable()
export class AccountService {
  constructor(
    private apiService: ApiService
  ) {
  }

  addAccount(userId: number, institutionId: number){
    this.apiService.get()
  }
}
