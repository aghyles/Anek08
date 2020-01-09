import { IFicheSeance } from 'app/shared/model/fiche-seance.model';

export interface IPresence {
  id?: number;
  idNageurAbs?: string;
  ficheSeance?: IFicheSeance;
}

export class Presence implements IPresence {
  constructor(public id?: number, public idNageurAbs?: string, public ficheSeance?: IFicheSeance) {}
}
