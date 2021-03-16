package com.tarmi.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tarmi.kotlin.databinding.FragmentProfileBinding
import com.tarmi.kotlin.BR.homeViewModel

class ProfileFragment : Fragment() {


    lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: HomeViewModel

    companion object {

        fun newInstance() = ProfileFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.setVariable(homeViewModel, viewModel)

        binding.firstButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }
}