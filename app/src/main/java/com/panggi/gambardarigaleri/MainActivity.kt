package com.panggi.gambardarigaleri


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Btn_pilihGambar.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                    val ijin = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(ijin, KODE_IJIN)
                } else {
                    ambilGambarGaleri()
                }
            } else {
                ambilGambarGaleri()
            }
        }
    }

    private fun ambilGambarGaleri(){
        val tujuan = Intent(Intent.ACTION_PICK)
        tujuan.type = "image/*"
        startActivityForResult(tujuan, KODE_AMBIL_GAMBAR)
    }

    companion object{
        private val  KODE_AMBIL_GAMBAR = 123
        private val KODE_IJIN = 321
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when  (requestCode){
            KODE_IJIN -> {
                if (grantResults.size > 0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    ambilGambarGaleri()
                }else{
                    Toast.makeText(
                        this,
                        "Ijin ditolak",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == KODE_AMBIL_GAMBAR){
            Iv_Gambar.setImageURI(data?.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}