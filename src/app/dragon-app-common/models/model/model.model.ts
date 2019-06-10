
import { Deserializable } from './../deserializable/deserializable.model'





export class Model implements Deserializable
{

    public deserialize (input : any) : this
    {

        Object.assign(this, input)
        return (this)

    }

}
