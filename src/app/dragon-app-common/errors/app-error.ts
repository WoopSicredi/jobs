
export class AppError
{

    protected _errors           : any
    protected _originalError    : any



    public constructor (errors ?: any, originalError ?: any)
    {

        this._errors        =   errors
        this._originalError =   originalError

    }



    public get errors ()
    {
        return (this._errors)
    }
    
    public get originalError ()
    {
        return (this._originalError)
    }



    public set errors (errors)
    {
        this._errors    =   errors
    }

}
