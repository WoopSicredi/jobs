
import { ErrorHandler } from '@angular/core'

import { LoginError }   from '../errors/login-error';





export class LoginErrorHandler implements ErrorHandler 
{

    constructor ()
    {

    }


    
    public handleError (error : LoginError) 
    {
        
        console.error('LOGIN ERROR HANDLER')

    }

}
