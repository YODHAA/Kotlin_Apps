package com.example.fragment_component

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {

    val LOG:String ="BlankFragment : "

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(LOG , "OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LOG , "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.i(LOG , "onCreateView")
        return inflater.inflate(R.layout.fragment_blank, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(LOG , "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG , "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG , "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG , "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG , "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(LOG , "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LOG , "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(LOG , "onDetach")
    }


}
