import { Moment } from 'moment';
import { IMesureAnthropo } from 'app/shared/model/mesure-anthropo.model';
import { IFicheTest } from 'app/shared/model/fiche-test.model';
import { IDocument } from 'app/shared/model/document.model';
import { IGroupe } from 'app/shared/model/groupe.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';

export interface ISwimer {
  id?: number;
  licenceNum?: string;
  firstName?: string;
  lastName?: string;
  sexe?: Sexe;
  bearthday?: Moment;
  phoneNumber?: string;
  eMail?: string;
  address?: string;
  studyTime?: string;
  firstSwim?: Moment;
  groupeName?: string;
  document?: number;
  mesureAnthropos?: IMesureAnthropo[];
  ficheTests?: IFicheTest[];
  documents?: IDocument[];
  groupe?: IGroupe;
}

export class Swimer implements ISwimer {
  constructor(
    public id?: number,
    public licenceNum?: string,
    public firstName?: string,
    public lastName?: string,
    public sexe?: Sexe,
    public bearthday?: Moment,
    public phoneNumber?: string,
    public eMail?: string,
    public address?: string,
    public studyTime?: string,
    public firstSwim?: Moment,
    public groupeName?: string,
    public document?: number,
    public mesureAnthropos?: IMesureAnthropo[],
    public ficheTests?: IFicheTest[],
    public documents?: IDocument[],
    public groupe?: IGroupe
  ) {}
}
