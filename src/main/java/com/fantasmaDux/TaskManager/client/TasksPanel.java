package com.fantasmaDux.TaskManager.client;

import com.fantasmaDux.TaskManager.api.ApiEndpoint;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TasksPanel extends VerticalPanel {
    private Label taskPanelLabel = new Label("Board's tasks");
    private VerticalPanel tasksContainer = new VerticalPanel();

    private GwtEntryPoint gwtEntryPoint;

    public TasksPanel(GwtEntryPoint entryPoint) {

        this.gwtEntryPoint = entryPoint;

        setSpacing(5);

        add(taskPanelLabel);
        add(tasksContainer);
    }

    protected void loadTasksByBoard(String boardId) {

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
                ApiEndpoint.TASKS_BY_BOARD + boardId);

        try {
            builder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == 200) {
                        parseTasks(response.getText());
                    } else {
                        Window.alert("Error loading tasks");
                    }
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    Window.alert("Request failed");
                }
            });

        } catch (RequestException e) {
            Window.alert("RequestException");
        }

    }

    private void parseTasks(String jsonText) {

        JSONObject json = JSONParser.parseStrict(jsonText).isObject();

        JSONArray tasksArray = json.get("data").isArray();

        tasksContainer.clear();

        for (int i = 0; i < tasksArray.size(); i++) {

            JSONObject taskJson = tasksArray.get(i).isObject();

            String taskId = taskJson.get("id").isString().stringValue();
            String title = taskJson.get("title").isString().stringValue();
            String description = taskJson.get("description").isString().stringValue();
            String statusStr = taskJson.get("status").isString().stringValue();
            StatusEnum status = StatusEnum.valueOf(statusStr);
            String priorityStr = taskJson.get("priority").isString().stringValue();
            PriorityEnum priority = PriorityEnum.valueOf(priorityStr);

            String dueDate = "";
            if (taskJson.get("dueDate") != null && !taskJson.get("dueDate").isString().stringValue().isEmpty()) {
                dueDate = taskJson.get("dueDate").isString().stringValue();
            }

            VerticalPanel taskPanel = new VerticalPanel();
            taskPanel.setSpacing(3);

            taskPanel.add(new Label("Title: " + title));
            taskPanel.add(new Label("Description: " + description));
            taskPanel.add(new Label("Status: " + status));
            taskPanel.add(new Label("Priority: " + priority));
            taskPanel.add(new Label("Due date: " + (dueDate != null ? dueDate.toString() : "N/A")));

            tasksContainer.add(taskPanel);
        }
    }
}
