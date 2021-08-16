package com.example.sampletrimming

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sampletrimming.databinding.FragmentSimpleCropBinding
import com.isseiaoki.simplecropview.CropImageView


class SimpleCropFragment : Fragment() {

    private lateinit var binding: FragmentSimpleCropBinding
    private var isCropped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSimpleCropBinding.inflate(inflater)
        binding.apply {
            cropView.imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.aneta)
            cropView.setCropMode(CropImageView.CropMode.FREE)
            buttonCrop.setOnClickListener {
                if (isCropped) {
                    cropView.visibility = View.GONE
                    imageView.setImageBitmap(cropView.croppedBitmap)
                    imageView.visibility = View.VISIBLE
                    buttonCrop.text = "RETURN"
                } else {
                    imageView.visibility = View.GONE
                    cropView.visibility = View.VISIBLE
                    buttonCrop.text = "CROP"
                }
                isCropped = !isCropped
            }
            buttonToUcrop.setOnClickListener {
                findNavController().navigate(R.id.action_simpleCropFragment_to_UCropFragment)
            }
        }
        return binding.root
    }

}