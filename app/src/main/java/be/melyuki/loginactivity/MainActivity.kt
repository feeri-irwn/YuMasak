package be.melyuki.loginactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var inputFormUser : EditText
    lateinit var inputFormPwd : EditText
    lateinit var btnValid : Button
    lateinit var btnReset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputFormUser = findViewById(R.id.et_main_username)
        inputFormPwd = findViewById(R.id.et_main_password)
        btnValid = findViewById(R.id.btn_main_valid)
        btnReset = findViewById(R.id.btn_main_reset)

        btnValid.setOnClickListener(this)
        btnReset.setOnClickListener(this)

        inputFormUser.addTextChangedListener {
            text -> lockLoginBtn()
        }
        inputFormPwd.addTextChangedListener {
            text -> lockLoginBtn()
        }

    }

    private fun lockLoginBtn() {

        val userData : String = inputFormUser.text.toString()
        val pwdData : String = inputFormPwd.text.toString()

//        if(userData.isBlank() || pwdData.isEmpty()) {
//            btnValid.isEnabled = false
//        }
//        else {
//            btnValid.isEnabled = true
//        }
        btnValid.isEnabled = !(userData.isBlank() || pwdData.isEmpty())
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_main_valid -> showResult()
            R.id.btn_main_reset -> eraseField("")
            else -> Toast.makeText(this, getString(R.string.msg_btn_error), Toast.LENGTH_LONG).show()
            }
        }

    private fun eraseField(msg : String) {
        inputFormUser.setText(msg)
        inputFormPwd.setText(msg)
        // Sans params dans la fonction...
//        inputFormUser.text.clear()
//        inputFormPwd.text.clear()

        inputFormUser.requestFocus()
//        inputFormUser.clearFocus()
//        inputFormPwd.clearFocus()
    }

    private fun showResult() {

        val userData : String = inputFormUser.text.toString()
        val pwdData : String = inputFormPwd.text.toString()

        if( userData == getString(R.string.valid_username) && pwdData == getString(R.string.valid_password)) {
            Toast.makeText(this, R.string.msg_btn_valid, Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, R.string.msg_btn_not_valid, Toast.LENGTH_LONG).show()

            inputFormPwd.text.clear()
            inputFormUser.requestFocus()
        }
    }
}
