package com.example.asteroidradarapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.example.asteroidradarapp.R
import com.example.asteroidradarapp.domain.AsteroidData
import com.example.asteroidradarapp.domain.NasaImageData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["property", "progressBar", "textView"])
fun bindImage(
    imgView: ImageView,
    property: NasaImageData?,
    progressBar: ProgressBar,
    textView: TextView
) {

    if (property?.mediaType == "image") {
        val imgUri = property.url.toUri().buildUpon().scheme("https").build()

        progressBar.visibility = View.VISIBLE

        Picasso.get().load(imgUri).into(imgView, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
                textView.visibility = View.VISIBLE
                textView.text = property.title
            }

            override fun onError(e: java.lang.Exception?) {

            }
        })
    } else if (property?.mediaType != "image" && !property?.mediaType.isNullOrBlank()) {
        progressBar.visibility = View.GONE
        textView.visibility = View.INVISIBLE
        textView.text = ""
        imgView.layoutParams.height = 0
    }
}

@BindingAdapter("codeName")
fun TextView.setCodeName(item: AsteroidData?) {
    item?.let {
        text = item.codename
    }
}

@BindingAdapter("closeApproachDate")
fun TextView.setCloseApproachDate(item: AsteroidData?) {
    item?.let {
        text = item.closeApproachDate
    }
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, item: AsteroidData?) {
    if (item?.isPotentiallyHazardous!!) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

