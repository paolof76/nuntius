import {Institution} from "./institution.model";
import {User} from "./user.model";

export interface Account {
  amount: number;
  client: User;
  currency: string;
  iban: string;
  id: number;
  institution: Institution;
  interestRate: string;
}
