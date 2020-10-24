package tn.thinkit.challenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_fragment.*
import tn.thinkit.challenge.R

class MainFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        continueClick()
    }

    private fun continueClick(){
        continueBtn.setOnClickListener {
            findNavController().navigate(R.id.toHome)
        }
    }
}