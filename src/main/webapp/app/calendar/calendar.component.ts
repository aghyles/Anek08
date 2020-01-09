import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Subject } from 'rxjs';
import { startOfDay, isSameDay, isSameMonth } from 'date-fns';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarMonthViewDay,
  CalendarView,
  DAYS_OF_WEEK
} from 'angular-calendar';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { fuseAnimations } from '@fuse/animations'; // /////////////
import { CalendarService } from 'app/calendar/calendar.service';
import { CalendarEventFormDialogComponent } from 'app/calendar/event-form/event-form.component';
import { CalendarEventModel } from 'app/calendar/event.model';
import { inspect } from 'util';

import {
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  addHours
} from 'date-fns';

@Component({
    selector     : 'jhi-calendar',
    templateUrl  : './calendar.component.html',
    styleUrls    : ['./calendar.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations   : fuseAnimations
})
export class CalendarComponent implements OnInit
{


  actions: CalendarEventAction[];
  confirmDialogRef: MatDialogRef<FuseConfirmDialogComponent>;
  dialogRef: any;

  viewS: string;

  locale : string;
  weekStartsOn: number = DAYS_OF_WEEK.SATURDAY;
  weekendDays: number[] = [DAYS_OF_WEEK.FRIDAY, DAYS_OF_WEEK.FRIDAY];

  // //////////////////////////////////////////////////////////////////
  view: CalendarView = CalendarView.Month;
  activeDayIsOpen: boolean;
  viewDate: Date = new Date();
  selectedDay: any;
  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [
    {
      title: 'Orque',
      start: subDays(startOfDay(new Date()), 1),
    },
    {
      title: 'Orque',
      start: new Date()
    }
  ];



  constructor(
    private _matDialog: MatDialog,
    private _calendarService: CalendarService
  )
  {



  // //////////////////////////////////////////////////////////////////


         // Set the defaults

         this.viewS = 'month';
         this.viewDate = new Date();
         this.activeDayIsOpen = true;
         this.selectedDay = {date: startOfDay(new Date())};

         this.actions = [
             {
                 label  : '<i class="material-icons s-16">edit</i>',
                 onClick: ({event}: { event: CalendarEvent }): void => {
                     this.editEvent('edit', event);
                 }
             },
             {
                 label  : '<i class="material-icons s-16">delete</i>',
                 onClick: ({event}: { event: CalendarEvent }): void => {
                     this.deleteEvent(event);
                 }
             }
         ];

         /**
          * Get events from service/server
*/
         this.setEvents();
     }

     // -----------------------------------------------------------------------------------------------------
     // @ Lifecycle hooks
     // -----------------------------------------------------------------------------------------------------

     /**
      * On init
      */
     ngOnInit(): void
     {
         /**
          * Watch re-render-refresh for updating db

         this.refresh.subscribe(updateDB => {
           if ( updateDB )
           {
             this._calendarService.updateEvents(this.events);
           }
         });

       this._calendarService.onEventsUpdated.subscribe(events => {
         this.setEvents();
         this.refresh.next();
       });*/
     }

     // -----------------------------------------------------------------------------------------------------
     // @ Public methods
     // -----------------------------------------------------------------------------------------------------
  eventClicked({ event }: { event: CalendarEvent }): void {
    // eslint-disable-next-line no-console
    console.log('Event clicked aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', event);
  }

  dayClicked(day: CalendarMonthViewDay): void
  {
    // eslint-disable-next-line no-console
    console.log('Event clicked bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb', event);
   this.addEvent();
  }
     /**
      * Set events
      */
     setEvents(): void
     {
       // this.events = this._calendarService.events;
     }

     /**
      * Before View Renderer
      *
      * @param {any} header
      * @param {any} body
      */
     beforeMonthViewRender({header, body}): void
     {
         /**
          * Get the selected day
          */
         const _selectedDay = body.find((_day) => {
             return _day.date.getTime() === this.selectedDay.date.getTime();
         });

         if ( _selectedDay )
         {
             /**
              * Set selected day style
              * @type {string}
          */
             _selectedDay.cssClass = 'cal-selected';
         }

     }

     /**
      * Day clicked
      *
      * @param {MonthViewDay} day

     dayClicked(day: CalendarMonthViewDay): void
     {
         const date: Date = day.date;
         const events: CalendarEvent[] = day.events;

         if ( isSameMonth(date, this.viewDate) )
         {
             if ( (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) || events.length === 0 )
             {
                 this.activeDayIsOpen = false;
             }
             else
             {
                 this.activeDayIsOpen = true;
                 this.viewDate = date;
             }
         }
         this.selectedDay = day;
         this.refresh.next();
     }
*/
     /**
      * Event times changed
      * Event dropped or resized
      *
      * @param {CalendarEvent} event
      * @param {Date} newStart
      * @param {Date} newEnd
      */
     eventTimesChanged({event, newStart, newEnd}: CalendarEventTimesChangedEvent): void
     {
         event.start = newStart;
         event.end = newEnd;
         // console.warn('Dropped or resized', event);
         this.refresh.next(true);
     }

     /**
      * Delete Event
      *
      * @param event
      */
     deleteEvent(event): void
     {
         this.confirmDialogRef = this._matDialog.open(FuseConfirmDialogComponent, {
             disableClose: false
         });

         this.confirmDialogRef.componentInstance.confirmMessage = 'Are you sure you want to delete?';

         this.confirmDialogRef.afterClosed().subscribe(result => {
             if ( result )
             {
                 const eventIndex = this.events.indexOf(event);
                 this.events.splice(eventIndex, 1);
                 this.refresh.next(true);
             }
             this.confirmDialogRef = null;
         });

     }

     /**
      * Edit Event
      *
      * @param {string} action
      * @param {CalendarEvent} event
      */
     editEvent(action: string, event: CalendarEvent): void
     {
         const eventIndex = this.events.indexOf(event);

         this.dialogRef = this._matDialog.open(CalendarEventFormDialogComponent, {
             panelClass: 'event-form-dialog',
             data      : {
                 event ,
                 action
             }
         });

         this.dialogRef.afterClosed()
             .subscribe(response => {
                 if ( !response )
                 {
                     return;
                 }
                 const actionType: string = response[0];
                 const formData: FormGroup = response[1];
                 switch ( actionType )
                 {
                     /**
                      * Save
                      */
                     case 'save':

                         this.events[eventIndex] = Object.assign(this.events[eventIndex], formData.getRawValue());
                         this.refresh.next(true);

                         break;
                     /**
                      * Delete
                      */
                     case 'delete':

                         this.deleteEvent(event);

                         break;
                 }
             });
     }

     /**
      * Add Event
      */
     addEvent(): void
     {
         this.dialogRef = this._matDialog.open(CalendarEventFormDialogComponent, {
             panelClass: 'event-form-dialog',
             data      : {
                 action: 'new',
                 date  : this.selectedDay.date
             }
         });
         this.dialogRef.afterClosed()
             .subscribe((response: FormGroup) => {
                 if ( !response )
                 {
                     return;
                 }
                 const newEvent = response.getRawValue();
                 newEvent.actions = this.actions;
                 this.events.push(newEvent);
                 this.refresh.next(true);
             });
     }



}


