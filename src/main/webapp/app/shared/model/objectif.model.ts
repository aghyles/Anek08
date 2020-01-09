import { IExercice } from 'app/shared/model/exercice.model';
import { ITheme } from 'app/shared/model/theme.model';

export interface IObjectif {
  id?: number;
  objectifName?: string;
  description?: string;
  exercices?: IExercice[];
  themes?: ITheme[];
}

export class Objectif implements IObjectif {
  constructor(
    public id?: number,
    public objectifName?: string,
    public description?: string,
    public exercices?: IExercice[],
    public themes?: ITheme[]
  ) {}
}
