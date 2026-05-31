import { CommonModule } from '@angular/common';
import { Component, inject, Inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginDemo } from '../model/login';
import { Login } from '../service/login';
import { Observable } from 'rxjs';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-user',
  imports: [FormsModule,CommonModule,RouterLink],
  templateUrl: './login-user.html',
  styleUrl: './login-user.css'
})
export class LoginUser{

 login: LoginDemo = new LoginDemo();

  service = inject(Login);

  constructor(private router: Router) {}
  loginUser() {
    this.service.loginUser(this.login).subscribe((res: string) => {
      console.log('Login response:', res);
      localStorage.setItem('loggedInEmail', this.login.email);
      this.router.navigate(['/dashboard']);
    }, (err) => {
      console.error('Login failed:', err);
      alert("Invalid email or password");
    });
  }

}
