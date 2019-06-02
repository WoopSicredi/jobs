
import { Deserializable }       from '../../dragon-app-common/models/deserializable/deserializable.model'
import { Model }                from '../../dragon-app-common/models/model/model.model'





export class HistoryModel extends Model implements Deserializable
{
    
    title       :   string
    description :   string



    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
