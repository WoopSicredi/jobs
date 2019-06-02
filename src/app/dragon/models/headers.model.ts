
import { Deserializable }       from '../../dragon-app-common/models/deserializable/deserializable.model'
import { Model }                from '../../dragon-app-common/models/model/model.model'





export class HeadersModel extends Model implements Deserializable
{
    
    normalizedNames ?:  any
    lazyUpdate      ?:  boolean | null



    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
