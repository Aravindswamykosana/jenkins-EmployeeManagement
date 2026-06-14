import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LoginUser } from '../login-user/login-user';
import { Observable } from 'rxjs';
import { LoginDemo } from '../model/login';
import { User1 } from '../model/User1';

@Injectable({
  providedIn: 'root'
})
export class Login {

   http = inject(HttpClient);

  loginUser(login: LoginDemo): Observable<string> {
    const params = new HttpParams()
      .set('mail', login.email)
      .set('pwd', login.password);

    return this.http.get("http://3.26.147.40:8081/login", { params, responseType: 'text' });
  }

    getUserByEmail(email: string): Observable<User1> {
    return this.http.get<User1>(`http://3.26.147.40:8081/getByEmail?email=${email}`);
  }

}
