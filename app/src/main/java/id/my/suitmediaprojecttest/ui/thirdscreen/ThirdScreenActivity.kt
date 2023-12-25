package id.my.suitmediaprojecttest.ui.thirdscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.my.suitmediaprojecttest.R
import id.my.suitmediaprojecttest.data.remote.response.DataItem
import id.my.suitmediaprojecttest.databinding.ActivityThirdScreenBinding
import id.my.suitmediaprojecttest.ui.ViewModelFactory
import id.my.suitmediaprojecttest.ui.secondscreen.SecondScreenActivity

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var thirdScreenViewModel: ThirdScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thirdScreenViewModel = obtainViewModel(this)

        setRecyclerView()
        setUsersData()
        setBtnBack()
    }

    private fun setBtnBack() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setUsersData() {
        val adapter = ThirdScreenAdapter()
        showLoading(true)
        binding.rvListUsers.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            },
            header = LoadingStateAdapter {
                adapter.retry()
            }
        )

        thirdScreenViewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
            showLoading(false)
        }

        val name = intent.getStringExtra(EXTRA_NAME)

        adapter.setOnItemClickCallback(object : ThirdScreenAdapter.OnItemClickCallback {

            override fun onItemClicked(data: DataItem?) {
                val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(
                    SecondScreenActivity.EXTRA_SELECTED_USER_NAME,
                    getString(R.string.full_name, data?.firstName, data?.lastName)
                )
                intent.putExtra(
                    SecondScreenActivity.EXTRA_NAME,
                    name
                )
                startActivity(intent)
            }
        })
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvListUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListUsers.addItemDecoration(itemDecoration)
    }

    private fun obtainViewModel(activity: AppCompatActivity): ThirdScreenViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ThirdScreenViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_NAME = "extra name"
    }
}