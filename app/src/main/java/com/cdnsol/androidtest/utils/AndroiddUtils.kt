package com.cdnsol.androidtest.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.cdnsol.androidtest.R
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException

class AndroiddUtils {
    companion object{
        private var mProgressDialog: ProgressDialog? = null

        fun showProgressDialog(mContext: Context?) {
            if (mProgressDialog != null) {
                mProgressDialog = null
            }
            Log.e("", "progress dailog start...")
            mProgressDialog = ProgressDialog(mContext, R.style.MyProgressDialogStyle)
            mProgressDialog!!.setMessage("Please wait...")
           mProgressDialog!!.setCancelable(false)
            if (mProgressDialog != null && !mProgressDialog!!.isShowing()) {
               mProgressDialog!!.show()
            }
        }
        fun hideProgressDialog(activity: Activity?) {
            if (activity != null && !activity.isFinishing
                && mProgressDialog != null && mProgressDialog!!.isShowing()
            ) {
                Log.e("", "dialog dismiss")
                mProgressDialog!!.dismiss()
                mProgressDialog = null
            }
        }

        fun customErrorCode(error: HttpException):Boolean{
            return (error.code()==401||error.code()==404)
        }
    }

}