import {Component, OnInit} from '@angular/core';
import {AccountService, InstitutionService, UserService} from "../core/services";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Institution} from "../core/models";

@Component({
  selector: 'app-account-add',
  templateUrl: './account-add.component.html',
})
export class AccountAddComponent implements OnInit {
  isSubmitting = false;
  accountForm: FormGroup;
  institutions: Institution[];

  constructor(
    private accountService: AccountService,
    private institutionService: InstitutionService,
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.accountForm = fb.group({
      institutionId: ['', Validators.required]
    });
    this.institutions = new Array<Institution>();
  }

  ngOnInit() {
    this.institutionService.getInstitutions()
      .subscribe(
        data => {
          this.institutions = data
        });
  }

  formSubmit() {
    this.isSubmitting = true;
    const currentUserId = this.userService.getCurrentUser().id;
    const institutionId = this.accountForm.value.institutionId;
    this.accountService.addAccount(currentUserId, institutionId)
      .subscribe(
        () => this.router.navigateByUrl('/account'),
        () => {
          this.isSubmitting = false;
        });
  }
}
