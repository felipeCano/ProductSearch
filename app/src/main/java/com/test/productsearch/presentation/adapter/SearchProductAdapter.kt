package com.test.productsearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.productsearch.R
import com.test.productsearch.data.model.ItemX
import com.test.productsearch.databinding.SearchProductListItemBinding

class SearchProductAdapter : RecyclerView.Adapter<SearchProductAdapter.SearchProductViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ItemX>() {
        override fun areItemsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem.imageInfo?.thumbnailUrl == newItem.imageInfo?.thumbnailUrl
        }

        override fun areContentsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductViewHolder {
        val binding = SearchProductListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        val searchProduct = differ.currentList[position]
        holder.bind(searchProduct)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SearchProductViewHolder(
        val binding: SearchProductListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchProduct: ItemX) {
            binding.productName.text = searchProduct.name
            binding.tvPrice.text = searchProduct.price.toString()
            Glide.with(binding.ivImage.context)
                .load(searchProduct.imageInfo?.thumbnailUrl)
                .error(R.drawable.placeholder_image)
                .into(binding.ivImage)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(searchProduct)
                }
            }

        }
    }

    private var onItemClickListener: ((ItemX) -> Unit)? = null

}