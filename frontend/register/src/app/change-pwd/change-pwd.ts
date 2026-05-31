import { Component, inject } from '@angular/core';
import { Change } from '../model/changePwd';
import { ChangeService } from '../service/change-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-pwd',
  imports: [FormsModule,CommonModule],
  templateUrl: './change-pwd.html',
  styleUrl: './change-pwd.css'
})
export class ChangePwd {
  change:Change=new Change();

  service=inject(ChangeService);

  successMsg = '';
  errorMsg = '';

  constructor(private router: Router) {}

  changePwd() {
  this.successMsg = '';
  this.errorMsg = '';

  this.service.changePwd(this.change).subscribe({
    next: (res: any) => {
      // If backend sends: { message: "Password changed successfully!" }
      if (res?.message === 'Password changed successfully!') {
        this.successMsg = res.message;
        this.change = new Change(); // reset form
        this.router.navigate(['/loginUser']);
      } else {
        // If backend sends something else even in 200 response
        this.errorMsg = res?.message || 'Password change failed. Please check your current password.';
      }
    },
    error: (err) => {
    this.errorMsg = err.error || 'Something went wrong';
  }
  });
}


}
