package com.studio.karya.inmuvi.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.utils.getSnapPosition
import kotlinx.android.synthetic.main.fragment_content.*
import org.jetbrains.anko.support.v4.ctx

class ContentFragment : Fragment() {

    private lateinit var adapter: ContentAdapter
    private lateinit var viewModel: ContentViewModel

    companion object {
        const val CONTENT_TYPE = "content_type"
    }

    fun newInstance(): Fragment {
        return ContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var snapPosition = RecyclerView.NO_POSITION

        activity?.let {
            viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)

            adapter = ContentAdapter(it)

            val typeContent = arguments?.getString(CONTENT_TYPE)
            if (typeContent == "movie") {
                adapter.setListContent(viewModel.getMovie(), "movie")
            } else {
                adapter.setListContent(viewModel.getTv(), "tv")
            }

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(rv_content)

            val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)

            rv_content.layoutManager = layoutManager
            rv_content.setHasFixedSize(true)
            rv_content.adapter = adapter

            rv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val snapPositions = snapHelper.getSnapPosition(recyclerView)
                    val snapPositionChanged = snapPosition != snapPositions
                    if (snapPositionChanged) {
                        snapPosition = snapPositions
                        title.text = when (typeContent) {
                            "movie" -> {
                                viewModel.getMovie()[snapPosition].titleContent
                            }
                            else -> {
                                viewModel.getTv()[snapPosition].titleContent
                            }
                        }
                    }
                }
            })
        }
    }
}
