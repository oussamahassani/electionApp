import { HttpInterceptor, HttpEvent, HttpHandler,HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { LoginService } from "./login/login.service";


@Injectable()
export class AuthIntercepter implements HttpInterceptor{

    constructor(private login:LoginService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       
        // add the jwt token to the request
        let authReq = req
        const token = this.login.getToken();
        let tokenStr :any = localStorage.getItem("token");
        let id :any = localStorage.getItem("user");
        if (token!=null) {
            authReq = authReq.clone({
                setHeaders: { Authorization :`Bearer  ${token}`},
        })
    }
        else if(tokenStr != null) {
            authReq = authReq.clone({
                setHeaders: { Authorization :`Bearer  ${tokenStr}`},
        })
        }

        
        console.log(token,"token")
    
        
        return next.handle(authReq);
    }
}

export const authIntercepterProvider=[
    {
        provide:HTTP_INTERCEPTORS,
        useClass:AuthIntercepter,
        multi:true,
    },
];