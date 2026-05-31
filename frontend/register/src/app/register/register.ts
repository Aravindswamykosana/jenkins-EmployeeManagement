import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegisterService } from '../service/register-service';
import { Register1 } from '../model/Register';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule,CommonModule,RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register{
    register:Register1=new Register1();

    service=inject(RegisterService);

    constructor(private router: Router) {}

    saveUser(){
      this.service.saveUser(this.register).subscribe((res:Register1)=>{
          this.register=res;
          this.router.navigate(['/loginUser']);
      },error=>{
      alert("api error..!")
    })
   }

   rest(){
    this.register=new Register1();
   }

   allowOnlyAlphabets(event: KeyboardEvent) {
  const charCode = event.key.charCodeAt(0);
  if (
    !(charCode >= 65 && charCode <= 90) &&
    !(charCode >= 97 && charCode <= 122)
  ) {
    event.preventDefault();
  }
}
}
