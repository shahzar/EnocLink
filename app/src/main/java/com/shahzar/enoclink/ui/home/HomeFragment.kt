package com.shahzar.enoclink.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.shahzar.enoclink.R
import com.shahzar.enoclink.ui.base.BaseFragment
import com.shahzar.enoclink.util.Constants
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutRes() = R.layout.home_fragment
    override fun getViewModelClass() = HomeViewModel::class.java
    override fun injectDependency() = getDiComponent().inject(this)


    override fun initViews() {
        viewModel.getSampleData()

        layoutProfileImage.setOnClickListener {
            promptImagePick()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.profileImageUri.observe(viewLifecycleOwner, Observer {
            displayProfileImage(it)
        })
    }

    private fun promptImagePick() {
        ImagePicker.with(this)
            .cropSquare()
            .compress(Constants.IMAGE_MAX_SIZE)
            .start()
    }

    private fun displayProfileImage(uri: Uri) {
        Glide.with(rootView)
            .load(uri)
            .circleCrop()
            .into(imgProfile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            viewModel.setSelectedImage(fileUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            showError(ImagePicker.getError(data))
        }
    }

}
