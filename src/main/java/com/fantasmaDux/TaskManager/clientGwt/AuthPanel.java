package com.fantasmaDux.TaskManager.clientGwt;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class AuthPanel extends VerticalPanel {

    private final TextBox firstNameField = new TextBox();
    private final TextBox lastNameField = new TextBox();
    private final TextBox emailField = new TextBox();
    private final PasswordTextBox passwordField = new PasswordTextBox();

    private final GwtEntryPoint gwtEntryPoint;

    public AuthPanel(final GwtEntryPoint gwtEntryPoint) {
        this.gwtEntryPoint = gwtEntryPoint;

        setSpacing(5);
        add(new Label("First Name:"));
        add(firstNameField);

        add(new Label("Last Name:"));
        add(lastNameField);

        add(new Label("Email:"));
        add(emailField);

        add(new Label("Password:"));
        add(passwordField);

        Button registerButton = new Button("Register");
        add(registerButton);

        registerButton.addClickHandler(event -> registerUser());

        Button loginButton = new Button("Login with Keycloak");
        add(loginButton);
        loginButton.addClickHandler(event -> {
            Window.Location.replace("/oauth2/authorization/keycloak");
        });
    }

    private void registerUser() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        JSONObject json = new JSONObject();
        json.put("firstName", new JSONString(firstName));
        json.put("lastName", new JSONString(lastName));
        json.put("email", new JSONString(email));
        json.put("password", new JSONString(password));


        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "/api/v1/auth/register");
        builder.setHeader("Content-Type", "application/json");

        try {
            builder.sendRequest(json.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == 200) {
                        Window.alert("Registration successful!");
                    } else {
                        Window.alert("Error: " + response.getText());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    Window.alert("Request failed: " + exception.getMessage());
                }
            });
        } catch (RequestException e) {
            Window.alert("Failed to send request: " + e.getMessage());
        }
    }
}
