package com.satyayudha0077.assessment_mobpro.ui.screen

import androidx.lifecycle.ViewModel
import com.satyayudha0077.assessment_mobpro.R
import com.satyayudha0077.assessment_mobpro.model.Buah

class MainViewModel : ViewModel() {
    val data = listOf(
        Buah(1,
            R.string.alpukat,
            R.drawable.alpukat,
            R.string.manfaat_alp
        ),
        Buah(2,
            R.string.apel,
            R.drawable.apel,
            R.string.manfaat_app
        ),
        Buah(3,
            R.string.jambu,
            R.drawable.jambu,
            R.string.manfaat_jam
        ),
        Buah(4,
            R.string.jeruk,
            R.drawable.jeruk,
            R.string.manfaat_jer
        ),
        Buah(5,
            R.string.pisang,
            R.drawable.pisang,
            R.string.manfaat_pis
        ),
        Buah(6,
            R.string.anggur,
            R.drawable.anggur,
            R.string.manfaat_ang
        ),
        Buah(7,
            R.string.durian,
            R.drawable.durian,
            R.string.manfaat_dur
        ),
        Buah(8,
            R.string.pepaya,
            R.drawable.pepaya,
            R.string.manfaat_pep
        ),
        Buah(9,
            R.string.mangga,
            R.drawable.mangga,
            R.string.manfaat_mang
        ),
        Buah(10,
            R.string.manggis,
            R.drawable.manggis,
            R.string.manfaat_gis
        ),
        Buah(11,
            R.string.melon,
            R.drawable.melon,
            R.string.manfaat_mel
        ),
        Buah(12,
            R.string.naga,
            R.drawable.naga,
            R.string.manfaat_nag
        ),
        Buah(13,
            R.string.strawberry,
            R.drawable.strawberry,
            R.string.manfaat_straw
        )
    )
}