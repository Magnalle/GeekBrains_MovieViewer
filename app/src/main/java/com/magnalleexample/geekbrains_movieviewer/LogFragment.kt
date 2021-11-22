package com.magnalleexample.geekbrains_movieviewer

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log, container, false)
        val text = view.findViewById<TextView>(R.id.log_text_view)
        text?.movementMethod = ScrollingMovementMethod()
        context?.app?.unBindLog()
        text?.text = context?.openFileInput("log.txt")?.let {
            val result = it.bufferedReader().readText().toString()
            it.close()
            result
        }
        context?.app?.bindLog()
        // Inflate the layout for this fragment
        return view
    }


}