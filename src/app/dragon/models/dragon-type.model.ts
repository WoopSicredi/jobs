
import { Deserializable } from './../../dragon-app-common/models/deserializable/deserializable.model'


export class DragonTypeModel implements Deserializable
{
    
    type    :   string



    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
