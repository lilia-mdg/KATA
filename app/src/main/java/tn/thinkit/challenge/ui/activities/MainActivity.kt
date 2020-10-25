@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package tn.thinkit.challenge.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.thinkit.challenge.R
import tn.thinkit.challenge.utilities.AlertDialog
import tn.thinkit.challenge.utilities.AlertDialogListener
import tn.thinkit.challenge.utilities.Animation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val onPositiveClicked = object :
            AlertDialogListener {
            override fun onClick() {
                finishAffinity()
            }
        }
        AlertDialog.openDialog(
            activity = this,
            title = "Exit application",
            message = "Do you really want to exit the application?",
            positiveTextBtn = getString(R.string.yes),
            negativeTextBtn = getString(R.string.no),
            animation = Animation.POP,
            onPositiveClicked = onPositiveClicked,
            onNegativeClicked = null,
            concellable = true
        )
    }

}