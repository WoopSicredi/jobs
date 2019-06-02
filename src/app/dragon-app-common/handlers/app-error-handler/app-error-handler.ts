
import { ErrorHandler } from '@angular/core'

import { LoginError }           from '../../auth/errors/login-error'
import { LoginErrorHandler }    from '../../auth/handlers/login-error-handler';





export class AppErrorHandler implements ErrorHandler 
{

    public handleError (originalError : any) 
    {
        
        if (originalError instanceof (LoginError)) {
            return (new LoginErrorHandler().handleError(<LoginError> originalError))
        }

        console.error(originalError)

    }

}
