package com.herdroid.ojekbro.utils

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.herdroid.ojekbro.fragment.BaseFragment
import com.herdroid.ojekbro.fragment.LoginFragment

fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean){
    visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if(enabled) 1f else 0.5f
}


fun View.snackbar(message:String, action:(()-> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()

}


fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry : (()->Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackbar(
            "Please Check Your Internet Connection",
            retry
        )
        failure.errorCode == 401 -> {
            if(this is LoginFragment)
            {
                requireView().snackbar("Check Email And Password")
                Log.d("the messge ","--------------");
            }
            else {
                // token expire logout appp
                (this as BaseFragment<*, *, *>)
            }
        }

        else ->{
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}
