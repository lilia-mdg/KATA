package tn.thinkit.challenge.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.room_fragment_row.view.*
import tn.thinkit.challenge.R
import tn.thinkit.challenge.data.model.Room
import tn.thinkit.challenge.utilities.loadingImage

class RoomAdapter(
    private val roomList: List<Room>,
    private val listener: RoomListener,
    private val activity: FragmentActivity
) :
    RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    interface RoomListener {
        fun onRoomClicked(room: Room, view: View)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.room_fragment_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = roomList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(roomList[position], listener, activity)

    }


    class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(
            room: Room,
            listener: RoomListener,
            activity: FragmentActivity
        ) {
            with(itemView) {
                roomName.text = room.name
                numberDevices.text = room.nbDevices.toString()
                devices.text = if (room.nbDevices > 1) "Devices" else "Device"

                activity.loadingImage(imageRoom, room.image)

                setOnClickListener {
                    listener.onRoomClicked(room, view)
                }
            }

        }
    }

}