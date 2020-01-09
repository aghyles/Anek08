import { IFicheTest } from 'app/shared/model/fiche-test.model';

export interface ITestPhysique {
  id?: number;
  testphyTitle?: string;
  testPhy1?: string;
  testPhy2?: string;
  testPhy3?: string;
  testPhy4?: string;
  testPhy5?: string;
  ficheTest?: IFicheTest;
}

export class TestPhysique implements ITestPhysique {
  constructor(
    public id?: number,
    public testphyTitle?: string,
    public testPhy1?: string,
    public testPhy2?: string,
    public testPhy3?: string,
    public testPhy4?: string,
    public testPhy5?: string,
    public ficheTest?: IFicheTest
  ) {}
}
