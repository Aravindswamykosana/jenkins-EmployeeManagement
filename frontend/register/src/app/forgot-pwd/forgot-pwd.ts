import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ForgotService } from '../service/forgot-service';
import { ForgotPwd1 } from '../model/forgotPwd1';

@Component({
  selector: 'app-forgot-pwd',
  imports: [FormsModule,CommonModule],
  templateUrl: './forgot-pwd.html',
  styleUrl: './forgot-pwd.css'
})
export class ForgotPwd {

  service=inject(ForgotService);
 
  forgot:ForgotPwd1=new ForgotPwd1();

  isLoader:boolean=false;

  forgotPwd(){
    this.isLoader=true;
    this.service.forgotPwd(this.forgot).subscribe((res:ForgotPwd1)=>{
         this.forgot=res;
         console.log(res);
         this.isLoader=false;
    }, (err) => {
          console.log(err);
         this.isLoader=false;

    })
  }
  
}
