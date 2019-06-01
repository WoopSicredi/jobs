
import { Deserializable } from './../../models/deserializable/deserializable.model'



export class LoginInfoModel implements Deserializable
{
    
    username    :   string
    password    :   string



    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
