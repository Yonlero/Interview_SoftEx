export interface AmountDataInputDTO {
  freight: number;
  receiver: string;
  discountInReal: number[];
  additionsInReal: number[];
  discountInPercent: number[];
  additionsInPercent: number[];
  mapPeople: Map<string, number[]>;
}
