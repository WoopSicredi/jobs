package br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_event_list.view.*
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListAdapter(private var items: List<EventData>) :
    RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_event_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(itemList: List<EventData>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(eventData: EventData) {
            itemView.listTitleTv.text = eventData.title
            val date = getDateTimeInstance()
            val dateString = date.format(Date(eventData.date))

            itemView.listDateTv.text =
                itemView.resources.getString(R.string.item_event_list_date, dateString)
            itemView.listPriceTv.text = itemView.resources.getString(R.string.item_event_list_price, eventData.price)
            Glide.with(itemView.context.applicationContext)
                .load(eventData.image)
                .placeholder(R.drawable.img_progress)
                .error(R.drawable.ic_broken_image)
                .into(itemView.eventListImageIv)
        }
    }


}