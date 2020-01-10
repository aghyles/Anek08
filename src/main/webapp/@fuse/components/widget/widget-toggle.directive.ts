import { Directive, ElementRef } from '@angular/core';

@Directive({
  // eslint-disable-next-line @typescript-eslint/tslint/config
  // tslint:disable-next-line:directive-selector
  selector: '[Jhi-fuseWidgetToggle]'
})
export class FuseWidgetToggleDirective {
  /**
   * Constructor
   *
   * @param {ElementRef} elementRef
   */
  constructor(public elementRef: ElementRef) {}
}
