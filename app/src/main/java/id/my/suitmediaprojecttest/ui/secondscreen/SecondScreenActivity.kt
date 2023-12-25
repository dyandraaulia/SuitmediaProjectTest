package id.my.suitmediaprojecttest.ui.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.my.suitmediaprojecttest.databinding.ActivitySecondScreenBinding
import id.my.suitmediaprojecttest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBinding()
    }

    private fun setBinding() {
        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = name

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            intent.putExtra(ThirdScreenActivity.EXTRA_NAME, name)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val selectedName = intent.getStringExtra(EXTRA_SELECTED_USER_NAME)
        if (selectedName != null) {
            binding.tvSelectedName.text = selectedName
        }
    }

    companion object {
        const val EXTRA_NAME = "extra name"
        const val EXTRA_SELECTED_USER_NAME = "extra selected user name"
    }
}