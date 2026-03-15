package com.fantasmaDux.TaskManager.clientGwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtEntryPoint implements EntryPoint {

    private DeckPanel mainDeck = new DeckPanel();

    private BoardsPanel boardsPanel;
    private String userId;

    @Override
    public void onModuleLoad() {
        AuthPanel authPanel = new AuthPanel(this);
        boardsPanel = new BoardsPanel(this);
//        TasksPanel tasksPanel = new TasksPanel(this);

        mainDeck.add(authPanel);
        mainDeck.add(boardsPanel);
//        mainDeck.add(tasksPanel);


        RootPanel.get().add(mainDeck);

        checkLogin();

    }

    private void checkLogin() {
        RequestBuilder builder =
                new RequestBuilder(RequestBuilder.GET, "/api/v1/auth/me");

        try {
            builder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(Request request, Response response) {

                    if (response.getStatusCode() == 200) {
                        JSONObject json =
                                JSONParser.parseStrict(response.getText()).isObject();

                        JSONObject data = json.get("data").isObject();

                        userId = data.get("id").isString().stringValue();

                        showBoards(userId);
                    } else {
                        showAuth();
                    }

                }

                @Override
                public void onError(Request request, Throwable exception) {
                    showAuth();
                }
            });

        } catch (RequestException e) {
            showAuth();
        }
    }

    public void showBoards(String userId) {
        boardsPanel.loadBoards(userId);
        mainDeck.showWidget(1);
    }

    public void showTasks(String boardId) {
        mainDeck.showWidget(2);
    }

    public void showAuth() {
        mainDeck.showWidget(0);
    }
}
