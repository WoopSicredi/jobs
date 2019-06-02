
import { DateModel }        from './../../dragon-app-common/models/date/date.model'
import { Deserializable }   from './../../dragon-app-common/models/deserializable/deserializable.model'
import { HeadersModel }     from './headers.model'
import { HistoryModel }     from './history.model'
import { Model }            from './../../dragon-app-common/models/model/model.model'
import { TypeModel }        from './type.model'





export class DragonModel extends Model implements Deserializable
{
    
    createdAt   :   DateModel
    headers    ?:   HeadersModel
    histories   :   HistoryModel[]
    id          :   number
    name        :   string
    type        :   TypeModel



    public deserialize (input : any) : this
    {
            
        Object.assign(this, input)

        this.createdAt  =   new DateModel().deserialize(input.createdAt)
        this.headers    =   new HeadersModel().deserialize(input.headers)
        this.histories  =   input.histories.map( (history) => new HistoryModel().deserialize(history) )
        this.type       =   new TypeModel().deserialize(input.type)

        return (this)

    }

}
