package com.cristianvillamil.platziwallet.ui.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristianvillamil.platziwallet.R
import kotlinx.android.synthetic.main.fragment_loans.*

class LoansFragment : Fragment() {

    private val adapter = LoansAdapter()
    private var loansViewModel : LoansViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        circularProgress.setProgressWithAnimation(
            70f,
            1000,
            AccelerateDecelerateInterpolator(),
            300
        )

        loansViewModel = ViewModelProviders.of(this).get(LoansViewModel::class.java)

        loansViewModel!!.getPercentageLiveData().observe(this, Observer {
            percentageText.text = it
        })

        loansViewModel!!.getLoansListLiveData().observe(this, Observer {
            adapter.setData(it)
        })

        loansViewModel!!.changeValues()
    }

    private fun initRecyclerView() {
        val loansList = listOf(
            Loan("", 50.toDouble(), 500.toDouble(), ""),
            Loan("", 50.toDouble(), 500.toDouble(), "")
        )
        loansRecyclerView.adapter = adapter
        loansRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setData(loansList)

    }
}