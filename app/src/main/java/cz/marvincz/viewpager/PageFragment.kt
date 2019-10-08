package cz.marvincz.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.page_test.*

class PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.text = arguments?.getString(ARG_TEXT)
    }

    companion object {
        private const val ARG_TEXT = "cz.marvincz.viewpager.TEXT"

        fun newInstance(text: String) = PageFragment().apply {
            arguments = bundleOf(ARG_TEXT to text)
        }
    }
}