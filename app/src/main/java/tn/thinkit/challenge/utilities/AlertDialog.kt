@file:Suppress("DEPRECATION")

package tn.thinkit.challenge.utilities

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import tn.thinkit.challenge.R

class AlertDialog private constructor(builder: Builder) {
    private val title: String?
    private val message: String?
    private val positiveBtnText: String?
    private val negativeBtnText: String?
    private val activity: Activity
    private val icon: Int
    private val visibility: Icon?
    private val animation: Animation?
    private val pListener: AlertDialogListener?
    private val nListener: AlertDialogListener?
    private val pBtnColor: Int
    private val bgColor: Int
    private val cancel: Boolean

    class Builder(internal val activity: Activity) {
        internal var title: String? = null
        internal var message: String? = null
        internal var positiveBtnText: String? = null
        internal var negativeBtnText: String? = null
        internal var icon = 0
        internal var visibility: Icon? = null
        internal var animation: Animation? = null
        internal var pListener: AlertDialogListener? = null
        internal var nListener: AlertDialogListener? = null
        internal var pBtnColor = 0
        internal var bgColor = 0
        internal var cancel = false
        internal var view: Int? = null
        var dialog: Dialog? = null

        fun setTitle(title: String?): Builder {
            this.title = title
            return this
        }

        fun setMessage(message: String?): Builder {
            this.message = message
            return this
        }

        fun setPositiveBtnText(positiveBtnText: String?): Builder {
            this.positiveBtnText = positiveBtnText
            return this
        }

        fun setNegativeBtnText(negativeBtnText: String?): Builder {
            this.negativeBtnText = negativeBtnText
            return this
        }

        fun setIcon(icon: Int, visibility: Icon?): Builder {
            this.icon = icon
            this.visibility = visibility
            return this
        }

        fun setAnimation(animation: Animation?): Builder {
            this.animation = animation
            return this
        }

        fun onPositiveClicked(pListener: AlertDialogListener?): Builder {
            this.pListener = pListener
            return this
        }

        fun onNegativeClicked(nListener: AlertDialogListener?): Builder {
            this.nListener = nListener
            return this
        }

        fun isCancellable(cancel: Boolean): Builder {
            this.cancel = cancel
            return this
        }

        fun build(): AlertDialog {
            dialog = when (animation) {
                Animation.POP -> Dialog(activity, R.style.PopTheme)
                Animation.SIDE -> Dialog(activity, R.style.SideTheme)
                Animation.SLIDE -> Dialog(activity, R.style.SlideTheme)
                else -> Dialog(activity)
            }
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCancelable(cancel)
            if (view == null) {
                dialog!!.setContentView(R.layout.dialog_alert)
                //getting resources
                val view: View = dialog!!.findViewById(R.id.background) as View
                val title1: TextView = dialog!!.findViewById<View>(R.id.title) as TextView
                val message1: TextView = dialog!!.findViewById<View>(R.id.message) as TextView
                val iconImg: ImageView = dialog!!.findViewById<View>(R.id.icon) as ImageView
                val nBtn: Button = dialog!!.findViewById<View>(R.id.negativeBtn) as Button
                val pBtn: Button = dialog!!.findViewById<View>(R.id.positiveBtn) as Button
                title1.text = title
                message1.text = message
                if (positiveBtnText != null)
                    pBtn.text = positiveBtnText
                if (pBtnColor != 0) {
                    val bgShape = pBtn.background
                    bgShape.setTint(pBtnColor)
                }
                if (negativeBtnText != null)
                    nBtn.text = negativeBtnText
                else
                    nBtn.visibility = View.GONE
                iconImg.setImageResource(icon)
                if (visibility == Icon.Visible)
                    iconImg.visibility =
                        View.VISIBLE
                else
                    iconImg.visibility = View.GONE
                if (bgColor != 0) view.setBackgroundColor(bgColor)
                if (pListener != null) {
                    pBtn.setOnClickListener {
                        pListener!!.onClick()
                        dialog!!.dismiss()
                    }
                } else {
                    pBtn.setOnClickListener { dialog!!.dismiss() }
                }
                if (nListener != null) {
                    nBtn.visibility = View.VISIBLE
                    nBtn.setOnClickListener {
                        nListener!!.onClick()
                        dialog!!.dismiss()
                    }
                } else {
                    nBtn.setOnClickListener { dialog!!.dismiss() }
                }
            }

            dialog!!.show()
            return AlertDialog(this)
        }
    }

    init {
        title = builder.title
        message = builder.message
        activity = builder.activity
        icon = builder.icon
        animation = builder.animation
        visibility = builder.visibility
        pListener = builder.pListener
        nListener = builder.nListener
        positiveBtnText = builder.positiveBtnText
        negativeBtnText = builder.negativeBtnText
        pBtnColor = builder.pBtnColor
        bgColor = builder.bgColor
        cancel = builder.cancel
    }

    companion object {

        private lateinit var mybuilder: Builder

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
        ): AlertDialog {
            mybuilder = Builder(
                activity
            )
                .setTitle(title)
                .setMessage(message)
                .setNegativeBtnText(negativeTextBtn)
                .setPositiveBtnText(positiveTextBtn)
                .setAnimation(animation)
                .isCancellable(concellable)
                .setIcon(
                    R.drawable.ic_launcher_foreground,
                    Icon.Visible
                )
                .onPositiveClicked(onPositiveClicked)
                .onNegativeClicked(onNegativeClicked)
            return mybuilder.build()
        }

    }

}


interface AlertDialogListener {
    fun onClick()
}

enum class Animation { POP, SIDE, SLIDE }
enum class Icon { Visible/*, Gone*/ }