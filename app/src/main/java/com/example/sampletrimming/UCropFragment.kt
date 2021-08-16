package com.example.sampletrimming

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sampletrimming.databinding.FragmentUCropBinding
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class UCropFragment : Fragment() {

    private lateinit var binding: FragmentUCropBinding

    companion object {
        const val REQUEST_MODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUCropBinding.inflate(inflater)
        binding.buttonUcrop.setOnClickListener {
            selectImage()
        }
        return binding.root
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        startActivityForResult(intent, REQUEST_MODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uri = data?.data
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_MODE) {
                if (uri != null) {
                    startCrop(uri)
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                val croppedUri = data?.let { UCrop.getOutput(it) }
                val bitmap = BitmapFactory.decodeFile(croppedUri?.path)
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun startCrop(uri: Uri) {
        val tmpFileName = UUID.randomUUID().toString() + ".jpg"
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val cropFile = File(dir, tmpFileName)
        val uCrop = UCrop.of(uri, Uri.fromFile(cropFile))
        uCrop.start(requireContext(), this)
    }

}