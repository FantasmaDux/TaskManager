package com.fantasmaDux.TaskManager.client;

import com.fantasmaDux.TaskManager.api.ApiEndpoint;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class BoardsPanel extends VerticalPanel {

    private Label boardPanelLabel = new Label("My boards");
    private VerticalPanel boardsContainer = new VerticalPanel();

    private GwtEntryPoint gwtEntryPoint;

    public BoardsPanel(GwtEntryPoint entryPoint) {
        this.gwtEntryPoint = entryPoint;

        setSpacing(5);

        add(boardPanelLabel);
        add(boardsContainer);

    }

    protected void loadBoards(String userId) {

        RequestBuilder builder =
                new RequestBuilder(RequestBuilder.GET,
                        ApiEndpoint.BOARDS_BY_USER + userId);

        try {

            builder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(Request request, Response response) {

                    if (response.getStatusCode() == 200) {

                        parseBoards(response.getText());

                    } else {

                        Window.alert("Error loading boards");

                    }

                }

                @Override
                public void onError(Request request, Throwable exception) {

                    Window.alert("Request failed");

                }

            });

        } catch (RequestException e) {

            Window.alert("Request exception");

        }
    }

    private void parseBoards(String jsonText) {

        JSONObject json = JSONParser.parseStrict(jsonText).isObject();

        JSONArray boardsArray = json.get("data").isArray();

        boardsContainer.clear();

        for (int i = 0; i < boardsArray.size(); i++) {

            JSONObject board = boardsArray.get(i).isObject();

            String boardId = board.get("id").isString().stringValue();
            String boardName = board.get("name").isString().stringValue();

            Button boardButton = new Button(boardName);

            boardButton.addClickHandler(e ->
                    gwtEntryPoint.showTasks(boardId)
            );

            boardsContainer.add(boardButton);
        }
    }
}
