import {User} from "./user.model";

export interface Account {
  id:number;
  institution: string;
  iban: string;
  amount: number;
  currency: string;
  interestRate: string;
  client: User;
}
