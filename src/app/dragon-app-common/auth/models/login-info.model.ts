
import { Deserializable }   from './../../models/deserializable/deserializable.model'
import { Model }            from './../../models/model/model.model'





export class LoginInfoModel extends Model implements Deserializable
{
    
    username    :   string
    password    :   string


    
    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
