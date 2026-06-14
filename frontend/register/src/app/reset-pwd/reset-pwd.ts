import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reset-pwd',
  imports: [FormsModule,CommonModule],
  templateUrl: './reset-pwd.html',
  styleUrl: './reset-pwd.css'
})
export class ResetPwd {
  token: string = '';
  newPwd: string = '';
  message: string = '';

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit() {
    this.token = this.route.snapshot.queryParamMap.get('token') || '';
  }

  resetPassword() {
    this.http.post(`http://3.26.147.40:8081/resetPwd?token=${this.token}&newPwd=${this.newPwd}`, null, { responseType: 'text' })
      .subscribe({
        next: (res) => this.message = res+" please go to login page",
        error: (err) => this.message = 'Something went wrong'
      });
  }
}

