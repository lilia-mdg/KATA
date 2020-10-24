package tn.thinkit.challenge.utilities

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import tn.thinkit.challenge.R

@SuppressLint("UseCompatLoadingForDrawables")
fun Activity.loadingImage(targetImageView: ImageView,@DrawableRes imageUrl: Int) {
    val animatedImage: Drawable =
        this.resources.getDrawable(
            R.drawable.home_loading,
            null
        )
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P && animatedImage is AnimatedImageDrawable) {
        val animation = animatedImage as AnimatedImageDrawable?
        animation?.start()
        targetImageView.setImageDrawable(animation)
        Glide.with(this)
            .load(imageUrl)
            .timeout(10000)
            .placeholder(animation)
            .error(R.color.gray)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    ActivityCompat.startPostponedEnterTransition(this@loadingImage)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    targetImageView.setImageDrawable(resource)
                    ActivityCompat.startPostponedEnterTransition(this@loadingImage)
                    return true
                }

            })
            .into(targetImageView)
    }
}