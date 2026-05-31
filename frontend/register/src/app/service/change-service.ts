import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Change } from '../model/changePwd';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChangeService {
  
  http=inject(HttpClient)

  changePwd(change: Change): Observable<any> {
  return this.http.post(
    `http://localhost:8081/change-password?email=${change.email}&currentPassword=${change.currentPwd}&newPassword=${change.newPwd}&confirmPassword=${change.confirmPwd}`,
    null,
    { responseType: 'json' } 
  );
}
}
