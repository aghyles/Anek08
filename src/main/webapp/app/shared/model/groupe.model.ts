import { Moment } from 'moment';
import { ISwimer } from 'app/shared/model/swimer.model';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { Category } from 'app/shared/model/enumerations/category.model';

export interface IGroupe {
  id?: number;
  groupeName?: string;
  startDate?: Moment;
  days?: string;
  endDate?: Moment;
  saison?: string;
  category?: Category;
  obs?: string;
  swimers?: ISwimer[];
  ficheSeances?: IFicheSeance[];
}

export class Groupe implements IGroupe {
  constructor(
    public id?: number,
    public groupeName?: string,
    public startDate?: Moment,
    public days?: string,
    public endDate?: Moment,
    public saison?: string,
    public category?: Category,
    public obs?: string,
    public swimers?: ISwimer[],
    public ficheSeances?: IFicheSeance[]
  ) {}
}
