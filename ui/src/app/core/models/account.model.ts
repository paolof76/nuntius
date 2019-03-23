import {Institution} from "./institution.model";

export interface Account {
  id: number;
  institution: Institution;
  iban: string;
  amount: number;
  currency: string;
  interestRate: string;
  creationDate: string;
  specialConditionsExpiresOn: string;
}
