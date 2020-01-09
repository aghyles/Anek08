import {Component, HostBinding, OnInit} from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd, NavigationError } from '@angular/router';

import { JhiLanguageHelper } from 'app/core/language/language.helper';
import {OverlayContainer} from "@angular/cdk/overlay";

@Component({
  selector: 'jhi-main',
  templateUrl: './main.component.html'
})
export class JhiMainComponent implements OnInit {
  openLeft = true;
  openRight = true;
  // toogle fuul screan
  @HostBinding('class') componentCssClass;
 temoin="my-theme";
  isAlternateMode= false;

  constructor(public overlayContainer: OverlayContainer,private jhiLanguageHelper: JhiLanguageHelper, private router: Router) {}

  onSetTheme() {
    this.isAlternateMode=!this.isAlternateMode;
   /* if (this.temoin==="my-theme"){
    this.overlayContainer.getContainerElement().classList.add("dark-theme");
    this.componentCssClass = "dark-theme";
    this.temoin="dark-theme";
   }else{
     this.overlayContainer.getContainerElement().classList.add("dark-theme");
     this.componentCssClass = "my-theme";
     this.temoin="my-theme";
   }*/
  }



  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : 't04JhApp';
    if (routeSnapshot.firstChild) {
      title = this.getPageTitle(routeSnapshot.firstChild) || title;
    }
    return title;
  }

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));
      }
      if (event instanceof NavigationError && event.error.status === 404) {
        this.router.navigate(['/404']);
      }
    });
  }
}
