import { IFicheTest } from 'app/shared/model/fiche-test.model';

export interface ITestEtude {
  id?: number;
  testTitle?: string;
  levelStudy?: string;
  average?: number;
  ficheTest?: IFicheTest;
}

export class TestEtude implements ITestEtude {
  constructor(
    public id?: number,
    public testTitle?: string,
    public levelStudy?: string,
    public average?: number,
    public ficheTest?: IFicheTest
  ) {}
}
