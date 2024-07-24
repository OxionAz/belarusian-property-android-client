package com.oxionaz.belarussian_property.other.util;

import android.widget.EditText;

public class TextInputCheck {

    public static boolean checkFields(EditText login, EditText password) {

        boolean isValid = true;

        if (login.getText().toString().isEmpty()) {
            login.setError("Введите логин");
            isValid = false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Введите пароль");
            isValid = false;
        }
        return isValid;
    }

    public static boolean checkFields(EditText login, EditText email, EditText password) {

        boolean isValid = true;

        if (login.getText().toString().isEmpty()) {
            login.setError("Введите логин");
            isValid = false;
        } else
        if (!login.getText().toString().matches("^[\\-_A-Za-z0-9]+$")) {
            login.setError("Латинские буквы, цифры, знак тире и подчеркивания");
            isValid = false;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Введите email");
            isValid = false;
        } else
        if (!email.getText().toString().matches("^[._A-Za-z0-9]+@[.\\-A-Za-z0-9]+.[A-Za-z]{2,6}$")) {
            email.setError("Некорректный email");
            isValid = false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Введите пароль");
            isValid = false;
        } else
        if (!password.getText().toString().matches("^[\\-_A-Za-z0-9]+$")) {
            password.setError("Латинские буквы, цифры, знак тире и подчеркивания");
            isValid = false;
        }
        return isValid;
    }
}
