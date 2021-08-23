package com.example.chuculture.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.chuculture.viewmodel.ImageTextViewModel
import com.example.chuculture.R
import com.example.chuculture.Utils.setCurrentItemLowSpeed
import com.example.chuculture.activity.HeroActivity
import com.example.chuculture.activity.MuseumActivity
import com.example.chuculture.activity.RelicActivity
import com.example.chuculture.adapter.BannerAdapter
import com.example.chuculture.adapter.HeroMainAdapter
import com.example.chuculture.adapter.MuseumAdapter
import com.example.chuculture.adapter.RelicMainAdapter
import com.example.chuculture.model.ThemeCollection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageTextFragment : Fragment() {

    private val viewModel by viewModels<ImageTextViewModel>()
    private val heroMainAdapter=HeroMainAdapter()
    private val relicAdapter=RelicMainAdapter()
    private val museumAdapter=MuseumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_text_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
        initObserve()
    }




    private fun initView(view: View) {
        val ryHero=view.findViewById<RecyclerView>(R.id.ry_hero)
        val ryMuseum=view.findViewById<RecyclerView>(R.id.ry_museum)
        val linearLayoutManagerMuseum=LinearLayoutManager(activity)
        val vpRelic=view.findViewById<ViewPager2>(R.id.vp_relic)
        val tvRelicMore=view.findViewById<TextView>(R.id.tv_relic_more)
        val tvMuseumMore=view.findViewById<TextView>(R.id.tv_museum_more)
        val tvHeroMore=view.findViewById<TextView>(R.id.tv_hero_more)

        ryHero.adapter=heroMainAdapter
        val linearLayoutManagerHero=LinearLayoutManager(activity)
        linearLayoutManagerHero.orientation=LinearLayoutManager.HORIZONTAL
        ryHero.layoutManager=linearLayoutManagerHero


        vpRelic.adapter=relicAdapter
        vpRelic.apply {
            offscreenPageLimit=1
            val recyclerView= getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.dp_10) +
                        resources.getDimensionPixelOffset(R.dimen.dp_10)
                setPadding(padding, 0, 8*padding, 0)
                clipToPadding = false
            }
        }


        linearLayoutManagerMuseum.orientation=LinearLayoutManager.HORIZONTAL
        ryMuseum.adapter=museumAdapter
        ryMuseum.layoutManager=linearLayoutManagerMuseum


        tvRelicMore.setOnClickListener {
            val intent= Intent(activity,RelicActivity::class.java)
            startActivity(intent)
        }

        tvMuseumMore.setOnClickListener {
            val intent=Intent(activity,MuseumActivity::class.java)
            startActivity(intent)
        }

        tvHeroMore.setOnClickListener {
            val intent=Intent(activity,HeroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData() {
        viewModel.getIndex()
    }

    private fun initObserve() {
        viewModel.response.observe(viewLifecycleOwner,{
            if (it.code==1&&it.data!=null){
                setBanner(it.data.themeCollectionList)
                heroMainAdapter.submitList(it.data.heroList)
                museumAdapter.submitList(it.data.museumList)
                relicAdapter.submitList(it.data.relicList)
            }
        })
    }


    private fun setBanner(themeCollectionList: List<ThemeCollection>) {
        if (view!=null){
            val vpBanner= requireView().findViewById<ViewPager2>(R.id.vp_banner)
            vpBanner.adapter=BannerAdapter(themeCollectionList)
            vpBanner.offscreenPageLimit=3
            lifecycleScope.launch {
                while (true){
                    var i=vpBanner.currentItem
                    vpBanner.setCurrentItemLowSpeed(++i,500L)
                    delay(2500L)
                }
            }
        }
    }
}