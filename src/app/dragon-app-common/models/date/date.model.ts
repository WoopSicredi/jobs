
import { Deserializable }   from '../deserializable/deserializable.model'
import { Model }            from '../model/model.model'





export class DateModel extends Model implements Deserializable
{

    date    :   string


    
    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
