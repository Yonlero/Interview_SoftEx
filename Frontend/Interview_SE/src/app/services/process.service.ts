import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AmountDataInputDTO } from '../types/AmountDataInputDTO.model';
import { AmountDataOutputDTO } from '../types/AmountDataOutputDTO.model';

@Injectable({
  providedIn: 'root',
})
export class ProcessService {
  private api_url = `${environment.base_api}/process`;
  constructor(private http: HttpClient) { }

  sendProcess(process: AmountDataInputDTO) {
    const params = new HttpParams().set('paymentMethod', 'PICPAY');
    return this.http.post<AmountDataOutputDTO>(this.api_url, process, {
      params: params,
    });
  }
}
