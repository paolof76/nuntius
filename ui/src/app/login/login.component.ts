import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../core/services';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  isSubmitting = false;
  loginForm: FormGroup;

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  submitForm() {
    this.isSubmitting = true;

    const credentials = this.loginForm.value;
    this.userService.login(credentials)
      .subscribe(
        () => this.router.navigateByUrl('/'),
        () => {
          this.isSubmitting = false;
        }
      );
  }
}
