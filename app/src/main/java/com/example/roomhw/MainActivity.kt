package com.example.roomhw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomhw.databinding.ActivityMainBinding
import com.example.roomhw.databinding.ItemBinding
import com.example.roomhw.items.ItemAdapter
import com.example.roomhw.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    private val adapter: ItemAdapter = ItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            viewModel.saveItem(binding.input.text.toString())
        }
        binding.listView.layoutManager = LinearLayoutManager(this)
        binding.listView.adapter = adapter
        viewModel.listLiveData.observe(this){
            adapter.submitList(it)
        }
        viewModel.listLiveData.observe(this){
            if(it.isNotEmpty()){
                binding.textView.isVisible = false
                binding.empty.isVisible = false
            }else{
                binding.textView.isVisible = true
                binding.empty.isVisible = true
            }
            binding.count.text = adapter.itemCount.toString()
        }
        adapter.click = {
            val alert = AlertDialog.Builder(this)
                .setMessage("You are done with this task?: \"${it.item}\"")
                .setTitle("Done?")
                .setPositiveButton("Ok") { _, _ ->
                    if(it.done){
                        viewModel.deleteById(it.id)
                    }
                    else{
                        viewModel.putMark(it.id)
                    }
                }
                .setNegativeButton("No") { _, _ ->

                }
                .create()

            alert.show()
        }

    }
}