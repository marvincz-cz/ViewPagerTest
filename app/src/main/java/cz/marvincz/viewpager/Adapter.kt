package cz.marvincz.viewpager

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private var list = listOf("A", "B")

    override fun getItemCount() = list.size

    override fun createFragment(position: Int) =
        PageFragment.newInstance(list[position])

    /**
     * Reverse the order of pages.
     */
    fun reorder() {
        list = list.reversed()
        notifyDataSetChanged()
    }
}