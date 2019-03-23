import {Injectable} from '@angular/core';

import {ApiService} from './api.service';
import {Institution} from "../models";
import {Observable} from "rxjs";

@Injectable()
export class InstitutionService {

  constructor(
    private apiService: ApiService
  ) {
  }

  getInstitutions(): Observable<Institution[]> {
    return this.apiService.get('/institution/all');
  }
}
