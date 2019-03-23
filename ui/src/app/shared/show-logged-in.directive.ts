import {Directive, Input, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';
import {UserService} from '../core/services';

@Directive({
  selector: '[appShowLoggedIn]'
})
export class ShowLoggedInDirective implements OnInit {
  condition: boolean;

  constructor(
    private templateRef: TemplateRef<any>,
    private userService: UserService,
    private viewContainer: ViewContainerRef
  ) {
  }

  @Input() set appShowLoggedIn(condition: boolean) {
    this.condition = condition;
  }

  ngOnInit() {
    this.userService.isLoggedIn.subscribe(
      (isLoggedIn) => {
        if (isLoggedIn && this.condition || !isLoggedIn && !this.condition) {
          this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
          this.viewContainer.clear();
        }
      }
    );
  }
}
