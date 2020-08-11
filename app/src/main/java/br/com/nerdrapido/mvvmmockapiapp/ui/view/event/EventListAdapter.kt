package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import br.com.nerdrapido.mvvmmockapiapp.ui.helper.ViewHelper
import kotlinx.android.synthetic.main.item_event_list.view.*

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListAdapter(
    private var items: List<Event>,
    private val viewHelper: ViewHelper,
    private val onItemClickListener: View.OnClickListener
) :
    RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_event_list, parent, false)
        val viewHolder = ViewHolder(view, viewHelper)
        viewHolder.setItemClickListener(onItemClickListener)
        return viewHolder
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

    class ViewHolder(itemView: View, private val viewHelper: ViewHelper) :
        RecyclerView.ViewHolder(itemView) {

        lateinit var event: Event

        private var onItemClickListener: View.OnClickListener? = null

        fun setItemClickListener(clickListener: View.OnClickListener) {
            onItemClickListener = clickListener
        }

        fun bind(eventBind: Event) {
            event = eventBind
            itemView.listTitleTv.text = eventBind.title
            itemView.listDateTv.text =
                itemView.resources.getString(R.string.item_event_list_date, event.date)
            itemView.listPriceTv.text =
                itemView.resources.getString(R.string.item_event_list_price, event.price)
            viewHelper.loadImageUrlIntoView(eventBind.image, itemView.eventListImageIv)
            itemView.tag = this
            itemView.setOnClickListener(onItemClickListener)
        }
    }


}