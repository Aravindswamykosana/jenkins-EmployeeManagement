import { inject, Injectable } from '@angular/core';
import { Register1 } from '../model/Register';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  http=inject(HttpClient)
  url = "http://localhost:8081/register";
  
  saveUser(reg:Register1):Observable<Register1>{
    return this.http.post<Register1>(this.url,reg);
  }

}
