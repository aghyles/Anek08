import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import {MatSidenav} from "@angular/material/sidenav";
//  import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss']
})
export class DashboardComponent implements OnInit {
  public isCollapsed = false;

  opened = true;@ViewChild('sidenav', { static: true }) sidenav: MatSidenav;
  //  calendarPlugins = [dayGridPlugin]; // important!
  constructor(config: NgbCarouselConfig) {
    // customize default values of carousels used by this component tree
    config.interval = 8000;
    //  config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

//  ngOnInit() {


 // }
// -------------------------------------------------------------------------------------------------------------------------




  ngOnInit() {
    // console.log(window.innerWidth)
    if (window.innerWidth < 768) {
      this.sidenav.fixedTopGap = 55;
      this.opened = false;
    } else {
      this.sidenav.fixedTopGap = 55;
      this.opened = true;
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    if (event.target.innerWidth < 768) {
      this.sidenav.fixedTopGap = 55;
      this.opened = false;
    } else {
      this.sidenav.fixedTopGap = 55
      this.opened = true;
    }
  }

  isBiggerScreen() {
    const width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    if (width < 768) {
      return true;
    } else {
      return false;
    }
  }



}
