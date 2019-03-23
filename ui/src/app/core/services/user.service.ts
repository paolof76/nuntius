import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, ReplaySubject} from 'rxjs';

import {User, Account} from '../models';
import {distinctUntilChanged, map} from 'rxjs/operators';
import {ApiService} from "./api.service";
import {HttpClient, HttpParams} from "@angular/common/http";


@Injectable()
export class UserService {
  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private isLoggedInSubject = new ReplaySubject<boolean>(1);
  public isLoggedIn = this.isLoggedInSubject.asObservable();

  constructor(
    private apiService: ApiService,
    private http: HttpClient
  ) {
    this.isLoggedInSubject.next(false);
  }

  login(credentials): Observable<User> {
    return this.apiService.get('/client/login?email=' + credentials.email)
      .pipe(map(
        data => {
          this.currentUserSubject.next({
            id: data,
            email: credentials.email,
            password: credentials.password
          } as User);
          this.isLoggedInSubject.next(true);
          return data;
        }
      ));
  }

  logout(): Observable<User> {
    this.currentUserSubject.next({} as User);
    this.isLoggedInSubject.next(false);
    return this.currentUser;
  }
}
