import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ForgotPwd1 } from '../model/forgotPwd1';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ForgotService {
  http=inject(HttpClient)

    forgotPwd(forgot:ForgotPwd1):Observable<ForgotPwd1>{
      return this.http.post<ForgotPwd1>(`http://3.27.43.34:8081/forgotPwd?email=${forgot.email}`,null);
    }
}
