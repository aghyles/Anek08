export interface IDosage {
  id?: number;
  dosageS?: string;
  dosagelong?: number;
}

export class Dosage implements IDosage {
  constructor(public id?: number, public dosageS?: string, public dosagelong?: number) {}
}
