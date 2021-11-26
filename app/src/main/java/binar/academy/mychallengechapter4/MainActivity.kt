package binar.academy.mychallengechapter4

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import binar.academy.mychallengechapter4.databinding.ActivityMainBinding

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnPemain = arrayOf(
            binding.ivPemainBatu,
            binding.ivPemainKertas,
            binding.ivPemainGunting,
        )

        val btnCom = arrayOf(
            binding.ivComBatu,
            binding.ivComKertas,
            binding.ivComGunting,
        )

        btnPemain.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                val hasilCom = btnCom.random()
                Log.d("${btnPemain[index].contentDescription}", "Clicked")
                hasilCom.setBackgroundResource(R.drawable.shape_backround)
                disableClick(false)
                cek(btnPemain[index].contentDescription, hasilCom.contentDescription)
                btnPemain.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                btnPemain[index].setBackgroundResource(R.drawable.shape_backround)
            }
        }
        binding.ivReset.setOnClickListener {
            Toast.makeText(this, "Mulai Lagi Broo...!", Toast.LENGTH_SHORT)
                .show()
            Log.d("reset", "Clicked")
            btnPemain.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            btnCom.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            binding.hasil.setBackgroundResource(android.R.color.transparent)
            binding.hasil.setTextColor(getColor(R.color.red))
            binding.hasil.setText(R.string.vs)
            disableClick(true)
        }

    }

    private fun cek(pemain: CharSequence, com: CharSequence) {
        if (pemain == com) {
            binding.hasil.setBackgroundColor(getColor(R.color.red))
            binding.hasil.setTextColor(getColor(R.color.black))
            binding.hasil.setText(R.string.seri)
        } else if (pemain == binding.ivPemainBatu.contentDescription && com == binding.ivComGunting.contentDescription || pemain == binding.ivPemainGunting.contentDescription && com == binding.ivComKertas.contentDescription || pemain == binding.ivPemainKertas.contentDescription && com == binding.ivComBatu.contentDescription) {
            binding.hasil.setBackgroundColor(getColor(R.color.blue_seri))
            binding.hasil.setTextColor(getColor(R.color.black))
            binding.hasil.setText(R.string.pemain_menang)
        } else {
            binding.hasil.setBackgroundColor(getColor(R.color.green_hasil))
            binding.hasil.setTextColor(getColor(R.color.black))
            binding.hasil.setText(R.string.pemain2_menang)
        }
        Log.d("Hasil", "$pemain VS $com")
    }

    private fun disableClick(isEnable: Boolean = false) {
        binding.ivPemainBatu.isEnabled = isEnable
        binding.ivPemainKertas.isEnabled = isEnable
        binding.ivPemainGunting.isEnabled = isEnable
    }

}
