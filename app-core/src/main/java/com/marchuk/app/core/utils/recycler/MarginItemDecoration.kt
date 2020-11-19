package com.marchuk.app.core.utils.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marchuk.app.core.utils.dp

/**
 * params spaceTop, spaceSide, spaceBottom - in dp
 */
class MarginItemDecoration(
    private val marginTop: Int,
    private val marginSide: Int,
    private val marginBottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = marginTop.dp
            bottom = marginBottom.dp
            left = marginSide.dp
            right = marginSide.dp
        }
    }

}

