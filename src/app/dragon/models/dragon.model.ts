
import { Deserializable }   from './../../dragon-app-common/models/deserializable/deserializable.model'
import { DragonType }       from './dragon-type.model'


export class DragonModel implements Deserializable
{
    
    createdAt   :   string
    headers    ?:   any[]
    histories   :   any[]
    id          :   number
    name        :   string
    type        :   DragonType



    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        this.type = new DragonType().deserialize(input.type)

        return (this)

    }

}
