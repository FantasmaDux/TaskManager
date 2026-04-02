package com.fantasmaDux.TaskManager.api;

public final class ApiEndpoint {
    private ApiEndpoint() {
    }

    public static final String BASE = "/api/v1";

    // Auth
    public static final String REGISTER = BASE + "/auth/register";
    public static final String ME = BASE + "/auth/me";

    // Boards
    public static final String BOARDS_BY_ID = BASE + "/boards/";
    public static final String BOARDS_BY_USER = BASE + "/boards/user/";

    // Tasks
    public static final String TASKS_BY_ID = BASE + "/tasks/";
    public static final String TASKS_BY_BOARD = BASE + "/tasks/board/";

    // Users
    public static final String USER_BY_ID = BASE + "/users/";

}
