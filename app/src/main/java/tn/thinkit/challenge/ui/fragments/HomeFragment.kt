@file:Suppress("DEPRECATION")

package tn.thinkit.challenge.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import tn.thinkit.challenge.R
import tn.thinkit.challenge.data.model.Room
import tn.thinkit.challenge.ui.adapters.RoomAdapter
import tn.thinkit.challenge.ui.viewmodels.HomeViewModel
import tn.thinkit.challenge.utilities.Constants
import tn.thinkit.challenge.utilities.InjectorUtils
import tn.thinkit.challenge.utilities.SharedPreferencesObject
import tn.thinkit.challenge.utilities.toSimpleString
import java.time.LocalDateTime

class HomeFragment : Fragment(), RoomAdapter.RoomListener {

    private var nameText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        getRoomsList()
        getName()
        displayTheTime()
    }

    @SuppressLint("SetTextI18n")
    private fun getName() {
        nameText = SharedPreferencesObject.getSharedPreferencesString(
            requireActivity(),
            Constants.SHARED_PREFERENCES_NAME,
            null
        )
        welcomeName.text = "Welcome, $nameText!"
    }

    private fun getRoomsList() {
        val factory = InjectorUtils.provideHomeViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        listRooms.apply {
            val mLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            layoutManager = mLayoutManager
            val roomList = viewModel.getRooms().value!!
            val roomAdapter =
                RoomAdapter(
                    roomList,
                    this@HomeFragment,
                    requireActivity()
                )
            adapter = roomAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayTheTime() {
        val now = LocalDateTime.now()
        date.text = toSimpleString(now)
    }


    override fun onRoomClicked(room: Room, view: View) {
        TODO("Not yet implemented")
    }
}