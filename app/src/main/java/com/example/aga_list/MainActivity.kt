package com.example.aga_list

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private lateinit var spinnerCategory: Spinner
    private lateinit var tvResult: TextView

    private var selectedDate = ""
    private var selectedTime = ""
    private var selectedCategory = ""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main) // Pastikan sesuai dengan XML yang benar

        // Inisialisasi komponen
        val btnPickDate = findViewById<Button>(R.id.btnPickDate)
        val btnPickTime = findViewById<Button>(R.id.btnPickTime)
        val btnSave = findViewById<Button>(R.id.btnSave)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        tvResult = findViewById(R.id.tvResult)

        // Data kategori kelas untuk Spinner
        val categories = arrayOf("Pilih Kategori", "Kelas A", "Kelas B", "Kelas C", "Kelas D")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        // Event pilih tanggal
        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = "Tanggal: $selectedDate"
            }, year, month, day)
            datePicker.show()
        }

        // Event pilih waktu
        btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvSelectedTime.text = "Waktu: $selectedTime"
            }, hour, minute, true)
            timePicker.show()
        }

        // Event tombol simpan
        btnSave.setOnClickListener {
            selectedCategory = spinnerCategory.selectedItem.toString()

            if (selectedDate.isEmpty() || selectedTime.isEmpty() || selectedCategory == "Pilih Kategori") {
                Toast.makeText(this, "Harap pilih tanggal, waktu, dan kategori!", Toast.LENGTH_SHORT).show()
            } else {
                tvResult.text = "Hasil Input:\nTanggal: $selectedDate\nWaktu: $selectedTime\nKategori: $selectedCategory"
            }
        }
    }
}
