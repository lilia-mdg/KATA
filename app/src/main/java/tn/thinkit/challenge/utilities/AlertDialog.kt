@file:Suppress("DEPRECATION")

package tn.thinkit.challenge.utilities

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import tn.thinkit.challenge.R

object AlertDialog {

    private var dialog: Dialog? = null

    fun openDialog(
        activity: Activity,
        title: String,
        message: String,
        positiveTextBtn: String?,
        negativeTextBtn: String?,
        animation: Animation,
        onPositiveClicked: AlertDialogListener?,
        onNegativeClicked: AlertDialogListener?,
        concellable: Boolean
    ) {
        if (dialog == null) {
            dialog = when (animation) {
                Animation.POP -> Dialog(activity, R.style.PopTheme)
                Animation.SIDE -> Dialog(activity, R.style.SideTheme)
                Animation.SLIDE -> Dialog(activity, R.style.SlideTheme)
            }
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCancelable(concellable)
            dialog!!.setContentView(R.layout.dialog_alert)

            val title1: TextView = dialog!!.findViewById<View>(R.id.title) as TextView
            val message1: TextView = dialog!!.findViewById<View>(R.id.message) as TextView
            val nBtn: Button = dialog!!.findViewById<View>(R.id.negativeBtn) as Button
            val pBtn: Button = dialog!!.findViewById<View>(R.id.positiveBtn) as Button
            title1.text = title
            message1.text = message
            if (positiveTextBtn != null)
                pBtn.text = positiveTextBtn
            if (negativeTextBtn != null)
                nBtn.text = negativeTextBtn
            else
                nBtn.visibility = View.GONE
            if (onPositiveClicked != null) {
                pBtn.setOnClickListener {
                    onPositiveClicked.onClick()
                    dialog!!.dismiss()
                }
            } else {
                pBtn.setOnClickListener {
                    dialog!!.dismiss()
                    dialog = null
                }
            }
            if (onNegativeClicked != null) {
                nBtn.visibility = View.VISIBLE
                nBtn.setOnClickListener {
                    onNegativeClicked.onClick()
                    dialog!!.dismiss()
                }
            } else {
                nBtn.setOnClickListener {
                    dialog!!.dismiss()
                    dialog = null
                }
            }
            dialog!!.show()
        }
    }
}

interface AlertDialogListener {
    fun onClick()
}

enum class Animation { POP, SIDE, SLIDE }