import { Component, inject } from '@angular/core';
import { Login } from '../service/login';
import { User1 } from '../model/User1';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

  service = inject(Login);

  user:User1=new User1();

   ngOnInit() {
    this.getEmail(); //caling data on loading
  }

  getEmail(){
    const email = localStorage.getItem('loggedInEmail');
    if (email) {
      this.service.getUserByEmail(email).subscribe((data:User1) => {
        console.log('User data:', data);
        this.user = data;
      }, (err) => {
        console.error('Failed to load user data', err);
      });
    } else {
      console.error('No email found in localStorage');
    }
  }

}
