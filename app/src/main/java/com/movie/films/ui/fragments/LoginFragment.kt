package com.movie.films.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.movie.films.R
import com.movie.films.mvp.model.api.ApiInterface
import com.movie.films.mvp.presenter.LoginFragmentPresenter
import com.movie.films.mvp.view.LoginFragmentView
import com.movie.films.ui.activity.MoviesActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LoginFragmentView, View.OnClickListener {

    @InjectPresenter
    lateinit var loginFragmentPresenter : LoginFragmentPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        etUsername.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                loginFragmentPresenter.usernameChanged(s.toString())
            }

        })
        btnGo.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnGo -> loginFragmentPresenter.goButtonClicked()
        }
    }

    override fun showUsernameIsEmptyMessage(message: Int) {
        tilUsername.error = getString(message)
    }

    override fun showErrorMessage(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun goToMoviesScreen() {
        val intent = Intent(activity, MoviesActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun showCreateNewUserDialog(username: String) {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(getString(R.string.attention))
        builder.setMessage(getString(R.string.do_you_want_to_create_new_user, username))
        builder.setPositiveButton(getString(android.R.string.ok)){dialog, which ->
            loginFragmentPresenter.onPositiveButtonClick(username)
            dialog.dismiss()
        }
        builder.setNegativeButton(getString(android.R.string.cancel)){dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}