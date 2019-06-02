
import { AppError }             from '../../dragon-app-common/errors/app-error'
import { DragonErrorFormat }    from './../interfaces/dragon-error-format'



export class DragonError extends AppError
{

    constructor (errors : DragonErrorFormat)
    {
        super(errors)
    }

}
