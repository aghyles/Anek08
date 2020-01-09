import { Directive, ElementRef } from '@angular/core';

@Directive({
    selector: '[Jhi-fuseWidgetToggle]'
})
export class FuseWidgetToggleDirective
{
    /**
     * Constructor
     *
     * @param {ElementRef} elementRef
     */
    constructor(
        public elementRef: ElementRef
    )
    {
    }
}
