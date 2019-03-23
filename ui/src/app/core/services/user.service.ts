import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, ReplaySubject} from 'rxjs';

import {User} from '../models';
import {distinctUntilChanged} from 'rxjs/operators';


@Injectable()
export class UserService {
  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private isLoggedInSubject = new ReplaySubject<boolean>(1);
  public isLoggedIn = this.isLoggedInSubject.asObservable();

  constructor(
  ) {
    this.isLoggedInSubject.next(false);
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  login(credentials): Observable<User> {
    const user = {email: credentials.email, password: credentials.password} as User;
    this.currentUserSubject.next(user);
    this.isLoggedInSubject.next(true);
    return this.currentUser;
  }

  logout(): Observable<User> {
    this.currentUserSubject.next({} as User);
    this.isLoggedInSubject.next(false);
    return this.currentUser;
  }

}
