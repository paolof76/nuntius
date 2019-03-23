import {User} from "./user.model";

export interface Account {
  id:number;
  institution: string;
  iban: string;
  client: User;
}
