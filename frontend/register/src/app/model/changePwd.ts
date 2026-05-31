export class Change{
    email:string;
    currentPwd:string;
    newPwd:string;
    confirmPwd:string;

    constructor(){
        this.email="";
        this.currentPwd="";
        this.newPwd="";
        this.confirmPwd="";
    }
}