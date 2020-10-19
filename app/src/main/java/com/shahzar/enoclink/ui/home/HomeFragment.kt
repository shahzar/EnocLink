package com.shahzar.enoclink.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.shahzar.enoclink.R
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.ui.base.BaseFragment
import com.shahzar.enoclink.util.Constants
import com.shahzar.enoclink.util.encrypt.EncryptUtil
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutRes() = R.layout.home_fragment
    override fun getViewModelClass() = HomeViewModel::class.java
    override fun injectDependency() = getDiComponent().inject(this)


    override fun initViews() {
        setTitle(getString(R.string.page_title_profile))
        viewModel.getUserDetails()

        layoutProfileImage.setOnClickListener {
            promptImagePick()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            displayUserDetails(it)
        })

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

    private fun displayUserDetails(user: UserResponse) {
        tvEmail.text = user.email
        tvPassword.text = EncryptUtil.aesDecrypt(user.password)
        if (user.avatarUrl.isNotEmpty()) {
            displayProfileImage(url = user.avatarUrl)
        }
    }

    private fun displayProfileImage(uri: Uri? = null,
                                    url: String? = null,
                                    drawable: Drawable? = ContextCompat.getDrawable(rootView.context, R.drawable.ic_blank_avatar)) {
        getImageLoader()
            .load(url?: uri?: drawable)
            .transition(DrawableTransitionOptions.withCrossFade(200))
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
