
import { DragonError }          from './dragon-error'
import { DragonErrorFormat }    from './../interfaces/dragon-error-format'



export class DragonNotFoundError extends DragonError
{

    constructor (errors : DragonErrorFormat)
    {
        super(errors)
    }

}
