package br.com.micaelpimentel.sicredevent.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import br.com.micaelpimentel.sicredevent.databinding.CheckinFormDialogBinding

class CheckinFormDialog(private val context: Context) {
    fun show(
        onCheckinButtonClicked: (name: String, email: String) -> Unit
    ) {
        CheckinFormDialogBinding
            .inflate(LayoutInflater.from(context)).apply {
                val dialog = AlertDialog.Builder(context)
                    .setView(root)
                    .create()

                checkinButton.setOnClickListener {
                    val name = nameEditText.text.toString()
                    val email = emailEditText.text.toString()
                    onCheckinButtonClicked(name, email)
                    dialog.dismiss()
                }

                dialog.show()
            }
    }
}