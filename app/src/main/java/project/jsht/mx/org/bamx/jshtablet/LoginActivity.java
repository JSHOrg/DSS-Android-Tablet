package project.jsht.mx.org.bamx.jshtablet;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import project.jsht.mx.org.bamx.jshtablet.Utils.ServiciosWeb;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private TextInputEditText  mEmailView;
    private TextInputEditText  mPasswordView;
    private View mProgressView;
    //private View mLoginFormView;
    private TextInputLayout emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (TextInputEditText) findViewById(R.id.email);
        mPasswordView = (TextInputEditText ) findViewById(R.id.password);

        emailInput = (TextInputLayout) findViewById(R.id.email_input);
        passwordInput = (TextInputLayout) findViewById(R.id.password_input);

        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Main(mEmailSignInButton);
            }
        });

        //mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void Main(View view) {

        boolean emailValid = new Utils(LoginActivity.this).submitForm("Default",mEmailView,emailInput);
        boolean passworValid = new Utils(LoginActivity.this).submitForm("Contraseña",mPasswordView,passwordInput);

        if (passworValid && emailValid){
            new ServiciosWeb(LoginActivity.this,view,ServiciosWeb.NombreServicioWeb.Login).LogIn(mEmailView.getText().toString(),mPasswordView.getText().toString());
            mEmailView.setText("");
            mPasswordView.setText("");
        }
    }
}