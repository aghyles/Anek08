import { Moment } from 'moment';
import { ITestAutre } from 'app/shared/model/test-autre.model';
import { ITestEtude } from 'app/shared/model/test-etude.model';
import { ITestNage } from 'app/shared/model/test-nage.model';
import { IDocument } from 'app/shared/model/document.model';
import { ITestPhysique } from 'app/shared/model/test-physique.model';
import { ISwimer } from 'app/shared/model/swimer.model';

export interface IFicheTest {
  id?: number;
  typeTest?: string;
  datetest?: Moment;
  testAutres?: ITestAutre[];
  testEtudes?: ITestEtude[];
  testNages?: ITestNage[];
  documents?: IDocument[];
  testPhysiques?: ITestPhysique[];
  swimer?: ISwimer;
}

export class FicheTest implements IFicheTest {
  constructor(
    public id?: number,
    public typeTest?: string,
    public datetest?: Moment,
    public testAutres?: ITestAutre[],
    public testEtudes?: ITestEtude[],
    public testNages?: ITestNage[],
    public documents?: IDocument[],
    public testPhysiques?: ITestPhysique[],
    public swimer?: ISwimer
  ) {}
}
