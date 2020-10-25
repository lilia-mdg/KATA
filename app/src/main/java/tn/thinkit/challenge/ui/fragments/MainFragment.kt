
package tn.thinkit.challenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_fragment.*
import tn.thinkit.challenge.R
import tn.thinkit.challenge.utilities.AlertDialog
import tn.thinkit.challenge.utilities.Animation
import tn.thinkit.challenge.utilities.Constants.SHARED_PREFERENCES_NAME
import tn.thinkit.challenge.utilities.SharedPreferencesObject.editSharedPreferencesString
import tn.thinkit.challenge.utilities.SharedPreferencesObject.getSharedPreferencesString


class MainFragment : Fragment() {
    private var nameText: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getName()
        if (nameText != null)
            findNavController().navigate(R.id.toHome)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        continueClick()
    }

    private fun continueClick() {
        continueBtn.setOnClickListener {
            if (nameEdittext.text.toString().isEmpty())
                AlertDialog.openDialog(
                    activity = requireActivity(),
                    title = getString(R.string.app_name),
                    message = "Please enter your name before continuing",
                    positiveTextBtn = "OK",
                    negativeTextBtn = null,
                    animation = Animation.POP,
                    onPositiveClicked = null,
                    onNegativeClicked = null,
                    concellable = false
                )
            else {
                setName(nameEdittext.text.toString())
                findNavController().navigate(R.id.toHome)
            }
        }
    }

    private fun setName(name: String) {
        editSharedPreferencesString(
            requireActivity(),
            SHARED_PREFERENCES_NAME,
            name
        )
    }

    private fun getName() {
        nameText = getSharedPreferencesString(
            requireActivity(),
            SHARED_PREFERENCES_NAME,
            null
        )
    }
}