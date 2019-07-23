package com.studio.karya.inmuvi.ui.content.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.source.remote.response.Tv
import com.studio.karya.inmuvi.data.source.remote.response.TvResponse
import com.studio.karya.inmuvi.ui.content.ContentViewModel
import com.studio.karya.inmuvi.utils.getSnapPosition
import com.studio.karya.inmuvi.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_content.*
import org.jetbrains.anko.support.v4.ctx

class TvFragment : Fragment(), TvCallback {

    private lateinit var adapter: TvAdapter
    private lateinit var viewModel: ContentViewModel

    var snapPosition = RecyclerView.NO_POSITION
    val snapHelper = PagerSnapHelper()

    fun newInstance(): Fragment {
        return TvFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = obtainViewModel(it)
            adapter = TvAdapter(it, this)

            viewModel.getTv().observe(this, getTv)

            val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            rv_content.layoutManager = layoutManager
            rv_content.setHasFixedSize(true)
            rv_content.adapter = adapter
            snapHelper.attachToRecyclerView(rv_content)
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): ContentViewModel {
        //Use a factory to inject dependencies into the view model
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(ContentViewModel::class.java)
    }

    private val getTv = Observer<TvResponse> {
        it.let {
            adapter.setListTv(it.tvList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun setTitleTv(tv: MutableList<Tv>) {
        rv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val snapPositions = snapHelper.getSnapPosition(recyclerView)
                val snapPositionChanged = snapPosition != snapPositions
                if (snapPositionChanged) {
                    snapPosition = snapPositions
                    title.text = tv[snapPositions].titleTv
                }
            }
        })
    }
}
