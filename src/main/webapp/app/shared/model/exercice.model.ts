import { IDosage } from 'app/shared/model/dosage.model';
import { IDocument } from 'app/shared/model/document.model';
import { IFicheSeance } from 'app/shared/model/fiche-seance.model';
import { IObjectif } from 'app/shared/model/objectif.model';

export interface IExercice {
  id?: number;
  exerciceName?: string;
  recomendation?: string;
  docSchema?: number;
  dosage?: IDosage;
  documents?: IDocument[];
  ficheSeances?: IFicheSeance[];
  objectifs?: IObjectif[];
}

export class Exercice implements IExercice {
  constructor(
    public id?: number,
    public exerciceName?: string,
    public recomendation?: string,
    public docSchema?: number,
    public dosage?: IDosage,
    public documents?: IDocument[],
    public ficheSeances?: IFicheSeance[],
    public objectifs?: IObjectif[]
  ) {}
}
