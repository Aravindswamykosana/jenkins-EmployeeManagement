import { Routes } from '@angular/router';
import { Register } from './register/register';
import { LoginUser } from './login-user/login-user';
import { Dashboard } from './dashboard/dashboard';
import { ForgotPwd } from './forgot-pwd/forgot-pwd';
import { ResetPwd } from './reset-pwd/reset-pwd';
import { ChangePwd } from './change-pwd/change-pwd';

export const routes: Routes = [
    {
        path:'',
        redirectTo:'loginUser',
        pathMatch:'full'
    },
    {
        path:'register',
        component: Register
    },
    {
        path:'loginUser',
        component:LoginUser
    },
    {
        path:'dashboard',
        component:Dashboard
    },
    {
        path:'forgot',
        component:ForgotPwd
    },
    {
        path:'reset-password',
        component: ResetPwd
    },
     {
        path:'change-pwd',
        component: ChangePwd
    }

];
