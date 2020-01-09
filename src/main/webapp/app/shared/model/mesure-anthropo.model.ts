import { Moment } from 'moment';
import { ISwimer } from 'app/shared/model/swimer.model';

export interface IMesureAnthropo {
  id?: number;
  date?: Moment;
  poids?: number;
  stature?: number;
  tailleAssis?: number;
  longTronc?: number;
  longMembreInferieurs?: number;
  longMembreSuperieur?: number;
  anvergureBras?: number;
  longBras?: number;
  longJambes?: number;
  longPieds?: number;
  hauteurPied?: number;
  longMain?: number;
  diamMain?: number;
  diamBiacromial?: number;
  diamBicretal?: number;
  diamThorax?: number;
  circThorax?: number;
  circThoraxInspirant?: number;
  circThoraxExpirant?: number;
  circBrasContracte?: number;
  circBrasDecontract?: number;
  circCuisse?: number;
  swimer?: ISwimer;
}

export class MesureAnthropo implements IMesureAnthropo {
  constructor(
    public id?: number,
    public date?: Moment,
    public poids?: number,
    public stature?: number,
    public tailleAssis?: number,
    public longTronc?: number,
    public longMembreInferieurs?: number,
    public longMembreSuperieur?: number,
    public anvergureBras?: number,
    public longBras?: number,
    public longJambes?: number,
    public longPieds?: number,
    public hauteurPied?: number,
    public longMain?: number,
    public diamMain?: number,
    public diamBiacromial?: number,
    public diamBicretal?: number,
    public diamThorax?: number,
    public circThorax?: number,
    public circThoraxInspirant?: number,
    public circThoraxExpirant?: number,
    public circBrasContracte?: number,
    public circBrasDecontract?: number,
    public circCuisse?: number,
    public swimer?: ISwimer
  ) {}
}
