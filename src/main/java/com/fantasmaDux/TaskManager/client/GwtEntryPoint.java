package com.fantasmaDux.TaskManager.client;

import com.fantasmaDux.TaskManager.api.ApiEndpoint;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtEntryPoint implements EntryPoint {

    private DeckPanel mainDeck = new DeckPanel();

    private static final int AUTH_SCREEN = 0;
    private static final int BOARDS_SCREEN = 1;

    private AuthPanel authPanel;
    private BoardsPanel boardsPanel;
    private TasksPanel tasksPanel;
    private String userId;

    @Override
    public void onModuleLoad() {
        authPanel = new AuthPanel(this);
        boardsPanel = new BoardsPanel(this);
        tasksPanel = new TasksPanel(this);

        // порядок добавления важен
        mainDeck.add(authPanel);
        mainDeck.add(boardsPanel);
        mainDeck.add(tasksPanel);


        RootPanel.get().add(mainDeck);

        checkLogin();

    }

    public void checkLogin() {
        RequestBuilder builder =
                new RequestBuilder(RequestBuilder.GET, ApiEndpoint.ME);

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

    private void showBoards(String userId) {
        boardsPanel.loadBoards(userId);
        mainDeck.showWidget(1);
    }

    protected void showTasks(String boardId) {
        tasksPanel.loadTasksByBoard(boardId);
        mainDeck.showWidget(2);
    }

    private void showAuth() {
        mainDeck.showWidget(AUTH_SCREEN);
    }
}
