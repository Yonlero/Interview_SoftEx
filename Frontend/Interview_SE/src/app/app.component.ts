import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

import { FormArray, FormBuilder, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatIconModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Interview_SE';

  form = this.fb.group({
    freight: [0.0],
    receiver: [""],
    discountInReal: this.fb.array([]),
    additionsInReal: this.fb.array([]),
    discountInPercent: this.fb.array([]),
    additionsInPercent: this.fb.array([]),
    mapPeople: this.fb.array([])
  });

  constructor(private fb: FormBuilder) {
    this.addDiscountInReal();
    this.addAdditionsInReal();
    this.addDiscountInPercent();
    this.addAdditionsInPercent();
  }

  get discountInReal() {
    return this.form.controls["discountInReal"] as FormArray;
  }
  addDiscountInReal() {
    const form = new FormControl();
    this.discountInReal.push(form)
  }

  get additionsInReal() {
    return this.form.controls["additionsInReal"] as FormArray;
  }
  addAdditionsInReal() {
    const form = new FormControl();
    this.additionsInReal.push(form)
  }

  get discountInPercent() {
    return this.form.controls["discountInPercent"] as FormArray;
  }
  addDiscountInPercent() {
    const form = new FormControl();
    this.discountInPercent.push(form)
  }

  get additionsInPercent() {
    return this.form.controls["additionsInPercent"] as FormArray;
  }
  addAdditionsInPercent() {
    const form = new FormControl();
    this.additionsInPercent.push(form)
  }

  get mapPeople() {
    return this.form.controls["mapPeople"] as FormArray;
  }

  addPerson() {
    const personGroup = this.fb.group({
      name: [''],
      values: this.fb.array([])
    });

    this.mapPeople.push(personGroup);
  }

  addValueToPerson(personIndex: number) {
    const values = this.mapPeople.at(personIndex).get('values') as FormArray;
    values.push(new FormControl());
  }

  getValues(personIndex: number): FormArray {
    return this.mapPeople.at(personIndex).get('values') as FormArray;
  }


  sendForm(): void {
    console.log(this.form.value);
  }

}
