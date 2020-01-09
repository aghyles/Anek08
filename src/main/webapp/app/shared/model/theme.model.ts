import { IObjectif } from 'app/shared/model/objectif.model';

export interface ITheme {
  id?: number;
  themeName?: string;
  description?: string;
  objectifs?: IObjectif[];
}

export class Theme implements ITheme {
  constructor(public id?: number, public themeName?: string, public description?: string, public objectifs?: IObjectif[]) {}
}
