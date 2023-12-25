package id.my.suitmediaprojecttest.ui.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.my.suitmediaprojecttest.R
import id.my.suitmediaprojecttest.databinding.ActivityFirstScreenBinding
import id.my.suitmediaprojecttest.ui.secondscreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBinding()
    }

    private fun setBinding() {
        binding.btnCheck.setOnClickListener {
            val palindrome = binding.inputPalindrome.text.toString()
            isPalindrome(palindrome)
        }

        binding.btnNext.setOnClickListener {
            val name = binding.inputName.text.toString()
            if (name.isNotEmpty()) {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Name should not empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPalindrome(palindrome: String) {
        val cleanPalindrome = palindrome.replace(" ", "").lowercase()
        setDialog(cleanPalindrome == cleanPalindrome.reversed(), palindrome)
    }

    private fun setDialog(isPalindrome: Boolean, palindrome: String) {
        if (!isFinishing) {
            val builder = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"

            builder.setTitle(palindrome)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

}