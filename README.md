# ViewPagerTest
Sample application and instrumented test to demonstrate *ViewPager2* *notifyDataSetChanged()* issues.

## Application structure
The application consists of a single activity with a ViewPager2 and a FAB.

The ViewPager is backed by a simple list and shows two pages, **A** and **B** (showing the corresponding letter).

Pressing the FAB reverses the order of the backing list.

## Expected behavior
Pressing the FAB should reverse the order of the pages. **A** **B** should change to **B** **A**

## The issue
After pressing the FAB, the currently visible page remains unchanged. Behavior of the other page depends on the notification method used.

If using *notifyDataSetChanged()* (currently used in the sample application), the second page changes if it was not previously shown. Otherwise it remains unchanged as well.

If using *notifyItemRangeChanged(Int, Int)*, the page not currently showing always changes. So, pressing the FAB
* when page **A** is showing changes **A** **B** to **A** **A**
* when page **B** is showing changes **A** **B** to **B** **B**

## Instrumented test
The application includes an instrumented test *ViewPagerTest* demonstrating the issue.
