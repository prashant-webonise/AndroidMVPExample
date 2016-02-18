package com.example.demo.androidmvpexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        presenter = new MainActivityPresenter(this);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText password = (EditText) findViewById(R.id.password);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updatePassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateData();
            }
        });
    }

    @Override
    public void updateName(String name) {

    }

    @Override
    public void updatePassword(String password) {

    }

    @Override
    public void isValidData() {
        //goto next activity
        Model model = presenter.getModel();
        Toast.makeText(this, model.getName(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, model.getPassword(), Toast.LENGTH_LONG).show();
    }
}

class MainActivityPresenter {

    private MainActivityView view;
    private Model model;

    public Model getModel() {
        return model;
    }

    public MainActivityPresenter(MainActivityView view) {
        if (view == null) {
            throw new IllegalStateException();
        }
        this.view = view;
        model = new Model();
    }

    void updateName(String name) {
        model.setName(name);
        view.updateName(name);
    }

    void updatePassword(String password) {
        model.setPassword(password);
        view.updatePassword(password);
    }

    void validateData() {
        if (!TextUtils.isEmpty(model.getName()) && !TextUtils.isEmpty(model.getPassword())) {
            view.isValidData();
        }
    }
}

interface MainActivityView {
    void updateName(String name);

    void updatePassword(String password);

    void isValidData();
}

class Model {
    private String name;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
