import { IFicheTest } from 'app/shared/model/fiche-test.model';

export interface ITestAutre {
  id?: number;
  testTitle?: string;
  obs1?: string;
  obs2?: string;
  obs3?: string;
  obs4?: string;
  obs5?: string;
  obs6?: string;
  obs7?: string;
  ficheTest?: IFicheTest;
}

export class TestAutre implements ITestAutre {
  constructor(
    public id?: number,
    public testTitle?: string,
    public obs1?: string,
    public obs2?: string,
    public obs3?: string,
    public obs4?: string,
    public obs5?: string,
    public obs6?: string,
    public obs7?: string,
    public ficheTest?: IFicheTest
  ) {}
}
