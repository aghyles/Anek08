import { Moment } from 'moment';
import { ISwimer } from 'app/shared/model/swimer.model';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { IExercice } from 'app/shared/model/exercice.model';
import { TypeDocument } from 'app/shared/model/enumerations/type-document.model';

export interface IDocument {
  id?: number;
  docTitle?: string;
  dateDoc?: Moment;
  linkDoc?: string;
  typeDoc?: TypeDocument;
  swimer?: ISwimer;
  ficheTest?: IFicheTest;
  exercices?: IExercice[];
}

export class Document implements IDocument {
  constructor(
    public id?: number,
    public docTitle?: string,
    public dateDoc?: Moment,
    public linkDoc?: string,
    public typeDoc?: TypeDocument,
    public swimer?: ISwimer,
    public ficheTest?: IFicheTest,
    public exercices?: IExercice[]
  ) {}
}
