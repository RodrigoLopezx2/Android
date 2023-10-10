package com.example.bluethoot

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.bluetooth.*
import android.content.*
import android.content.pm.PackageManager
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.*
const val REQUEST_ENABLE_BT = 1

class MainActivity : AppCompatActivity() {
    lateinit var mBtAdapter: BluetoothAdapter
    var mAddressDevices: ArrayAdapter<String>? = null
    var mNameDevices: ArrayAdapter<String>? = null
    companion object{
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private var m_bluetoothSocket: BluetoothSocket? = null
        var m_isConnected: Boolean = false
        lateinit var m_address: String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT),0);
        mAddressDevices = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        mNameDevices = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        val idBtnOnBT = findViewById<Button>(R.id.idBtnOnBT)
        val idBtnOffBT = findViewById<Button>(R.id.idBtnOffBT)
        val idBtnConect = findViewById<Button>(R.id.idBtnConect)
        val idBtnEnviar = findViewById<Button>(R.id.idBtnEnviar)
        val idBtnLuz_1on = findViewById<Button>(R.id.idBtnLuz_1on)
        val idBtnLuz_1off = findViewById<Button>(R.id.idBtnLuz_1off)
        val idBtnLuz_2on = findViewById<Button>(R.id.idBtnLuz_2on)
        val idBtnLuz_2off = findViewById<Button>(R.id.idBtnLuz_2off)
        val idBtnDispBT = findViewById<Button>(R.id.idBtnDispBT)
        val idSpinDisp = findViewById<Spinner>(R.id.idSpinDisp)
        val idTextOut = findViewById<EditText>(R.id.idTextOut)
        val someActivityResultLauncher = registerForActivityResult( StartActivityForResult() ){
                result ->
            if(result.resultCode == REQUEST_ENABLE_BT) {
                Log.i("MainActivity", "Registered Activity")
            }
        }
        mBtAdapter = (getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        if(mBtAdapter == null){
            Toast.makeText(this, "BT no est� disponible en este dispositivo", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, "BT s� est� disponible en este dispositivo", Toast.LENGTH_LONG).show()
        }
        idBtnOnBT.setOnClickListener {
            if(mBtAdapter.isEnabled){
                Toast.makeText(this, "BT ya est� habilitado", Toast.LENGTH_LONG).show()
            } else {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (ActivityCompat.checkSelfPermission( this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                    Log.i("MainActivity", "ActivityCompat#RequestPermissions")
                }
                someActivityResultLauncher.launch(enableBtIntent)
            }
        }
        idBtnOffBT.setOnClickListener {
            if (!mBtAdapter.isEnabled) {
                Toast.makeText(this, "BT ya est� desactivado", Toast.LENGTH_LONG).show()
            } else {
                mBtAdapter.disable()
                Toast.makeText(this, "BT se ha deseactivado", Toast.LENGTH_LONG).show()
            }
        }
        idBtnDispBT.setOnClickListener {
            if(mBtAdapter.isEnabled) {
                val pairedDevices: Set<BluetoothDevice>? = mBtAdapter?.bondedDevices
                mAddressDevices!!.clear()
                mNameDevices!!.clear()
                pairedDevices?.forEach { device ->
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC Address
                    mAddressDevices!!.add(deviceHardwareAddress)
                    mNameDevices!!.add(deviceName)
                }
                idSpinDisp.setAdapter(mNameDevices)
            } else {
                val noDevices = "Ning�n dispositivo se pudo emparejar"
                mAddressDevices!!.add(noDevices)
                mNameDevices!!.add(noDevices)
                Toast.makeText(this, "Primero debe activarse BT", Toast.LENGTH_LONG).show()
            }
        }
        idBtnConect.setOnClickListener {
            try {
                if (m_bluetoothSocket == null || !m_isConnected) {
                    val IntValSpin = idSpinDisp.selectedItemPosition
                    m_address = mAddressDevices!!.getItem(IntValSpin).toString()
                    Toast.makeText(this, m_address, Toast.LENGTH_LONG).show()
                    mBtAdapter?.cancelDiscovery()
                    val device: BluetoothDevice = mBtAdapter.getRemoteDevice(m_address)
                    m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    m_bluetoothSocket!!.connect()
                }
                Toast.makeText(this, "CONEXION EXITOSA", Toast.LENGTH_LONG).show()
                Log.i("MainActivity", "CONEXION EXITOSA")
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "CONEXION NO EXITOSA", Toast.LENGTH_LONG).show()
                Log.i("MainActivity", "CONEXION NO EXITOSA")
            }
        }
        idBtnLuz_1on.setOnClickListener {
            sendCommand("A")
        }
        idBtnLuz_1off.setOnClickListener {
            sendCommand("B")
        }
        idBtnLuz_2on.setOnClickListener {
            sendCommand("C")
        }
        idBtnLuz_2off.setOnClickListener {
            sendCommand("D");
        }
        idBtnEnviar.setOnClickListener {
            if(idTextOut.text.toString().isEmpty()){
                Toast.makeText(this, "Se debe incluir alg�n texto", Toast.LENGTH_SHORT)
            } else{
                var mensaje_out: String = idTextOut.text.toString()
                sendCommand(mensaje_out)
            }
        }
    }
    private fun sendCommand(input: String){
        if(m_bluetoothSocket != null){
            try{
                m_bluetoothSocket!!.outputStream.write(input.toByteArray())
            } catch(e: IOException){
                e.printStackTrace()
            }
        }
    }
}