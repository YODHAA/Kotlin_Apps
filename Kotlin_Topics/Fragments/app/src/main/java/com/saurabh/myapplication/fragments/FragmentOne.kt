package com.saurabh.myapplication.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saurabh.myapplication.R
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {

    val TAG = "Fragment One : "

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "on Attach called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "on Create  called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "on CreateView  called")
        return inflater!!.inflate(R.layout.fragment_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragment_one.setBackgroundColor(Color.WHITE)
        Log.d(TAG, "on ActivityCreated  called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "on onStart  called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "on onResume  called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "on OnStop  called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "on onDestroy  called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "on onDestryView  called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "on onDeattach  called")
    }

}