package br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_event_list.view.*

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListAdapter(private var items: List<Event>) :
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

    fun setItems(itemList: List<Event>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(eventData: Event) {
            itemView.listTitleTv.text = eventData.title
            itemView.listDateTv.text =
                itemView.resources.getString(R.string.item_event_list_date, eventData.date)
            itemView.listPriceTv.text =
                itemView.resources.getString(R.string.item_event_list_price, eventData.price)
            Glide.with(itemView.context.applicationContext)
                .load(eventData.image)
                .placeholder(R.drawable.img_progress)
                .error(R.drawable.ic_broken_image)
                .into(itemView.eventListImageIv)
        }
    }


}