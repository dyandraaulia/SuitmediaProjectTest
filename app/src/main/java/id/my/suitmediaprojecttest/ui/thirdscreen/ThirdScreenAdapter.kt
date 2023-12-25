package id.my.suitmediaprojecttest.ui.thirdscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import id.my.suitmediaprojecttest.data.remote.response.DataItem
import id.my.suitmediaprojecttest.databinding.ItemUserBinding

class ThirdScreenAdapter :
    PagingDataAdapter<DataItem, ThirdScreenAdapter.ThirdScreenViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ThirdScreenViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: DataItem?, clickListener: OnItemClickCallback) {
            binding.tvEmail.text = result?.email
            binding.tvUserName.text = buildString {
                append(result?.firstName)
                append(result?.lastName)
            }
            Glide.with(binding.root.context)
                .load(result?.avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(binding.ivProfilePicture)
            binding.root.setOnClickListener {
                clickListener.onItemClicked(result)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThirdScreenViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThirdScreenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThirdScreenViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story, onItemClickCallback)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem?)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}