import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {UserService} from '../core/services';
import {Observable} from 'rxjs';
import {map, take} from 'rxjs/operators';

@Injectable()
export class NoLoginGuard implements CanActivate {
  constructor(
    private router: Router,
    private userService: UserService
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {

    return this.userService.isLoggedIn.pipe(take(1), map(isAuth => !isAuth));

  }
}
