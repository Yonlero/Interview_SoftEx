export interface AmountDataOutputDTO {
  totalWithoutDiscountOrAdditions: number;
  totalWithDiscountAndAdditions: number ;
  freight: number;
  receiver: string ;
  discountInReal: number[];
  additionsInReal: number[];
  discountInPercent: number[];
  additionsInPercent: number[];
  mapAmountByPeople: Map<string, any[]>;
}
