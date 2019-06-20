package com.studio.karya.inmuvi.ui.movie


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.ContentEntity
import com.studio.karya.inmuvi.utils.OnSnapPositionChangeListener
import com.studio.karya.inmuvi.utils.getSnapPosition
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.ctx


class MovieFragment : Fragment(), MovieCallback {

    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    companion object {
        private const val TAG = "MovieFragment"
    }

    fun newInstance(): Fragment {
        return MovieFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var snapPosition = RecyclerView.NO_POSITION

        activity?.let {
            viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

            adapter = MovieAdapter(it, this)
            adapter.setListContent(viewModel.getMovie())

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(rv_movie)

            val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)

            rv_movie.layoutManager = layoutManager
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = adapter

            rv_movie.addOnScrollListener(object: RecyclerView.OnScrollListener(){

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val snapPositions = snapHelper.getSnapPosition(recyclerView)
                    val snapPositionChanged = snapPosition != snapPositions
                    if (snapPositionChanged){
                        snapPosition = snapPositions
                        title.text = viewModel.getMovie()[snapPosition].titleContent
                    }
                }
            })
        }
    }

    override fun listenerContent(content: ContentEntity) {
        title.text = content.titleContent
    }
}
