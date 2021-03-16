package com.tarmi.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tarmi.kotlin.databinding.HomeFragmentBinding
import com.tarmi.kotlin.BR.homeViewModel

class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    companion object{
        fun newInstance() = HomeFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.setVariable(homeViewModel, viewModel)


        binding.predji.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_profileFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container, false)

        binding.setLifecycleOwner(this)


        return binding.root
    }


}