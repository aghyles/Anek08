import { IFicheTest } from 'app/shared/model/fiche-test.model';

export interface ITestNage {
  id?: number;
  testTitle?: string;
  nl50?: number;
  nl100?: number;
  nl200?: number;
  nl400?: number;
  nl800?: number;
  nl1500?: number;
  pap50?: number;
  pap100?: number;
  pap200?: number;
  dos50?: number;
  dos100?: number;
  dos200?: number;
  brasse50?: number;
  brasse100?: number;
  brasse200?: number;
  na4ge100?: number;
  na4ge200?: number;
  na4ge400?: number;
  h1nl?: number;
  autre?: string;
  ficheTest?: IFicheTest;
}

export class TestNage implements ITestNage {
  constructor(
    public id?: number,
    public testTitle?: string,
    public nl50?: number,
    public nl100?: number,
    public nl200?: number,
    public nl400?: number,
    public nl800?: number,
    public nl1500?: number,
    public pap50?: number,
    public pap100?: number,
    public pap200?: number,
    public dos50?: number,
    public dos100?: number,
    public dos200?: number,
    public brasse50?: number,
    public brasse100?: number,
    public brasse200?: number,
    public na4ge100?: number,
    public na4ge200?: number,
    public na4ge400?: number,
    public h1nl?: number,
    public autre?: string,
    public ficheTest?: IFicheTest
  ) {}
}
