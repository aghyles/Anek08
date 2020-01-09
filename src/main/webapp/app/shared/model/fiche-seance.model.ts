import { Moment } from 'moment';
import { IPresence } from 'app/shared/model/presence.model';
import { IExercice } from 'app/shared/model/exercice.model';
import { IGroupe } from 'app/shared/model/groupe.model';

export interface IFicheSeance {
  id?: number;
  ficheSNum?: number;
  date?: Moment;
  observation?: string;
  volume?: number;
  bilan?: string;
  presences?: IPresence[];
  exercices?: IExercice[];
  groupe?: IGroupe;
}

export class FicheSeance implements IFicheSeance {
  constructor(
    public id?: number,
    public ficheSNum?: number,
    public date?: Moment,
    public observation?: string,
    public volume?: number,
    public bilan?: string,
    public presences?: IPresence[],
    public exercices?: IExercice[],
    public groupe?: IGroupe
  ) {}
}
