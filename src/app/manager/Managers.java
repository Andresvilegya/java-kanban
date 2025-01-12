package app.manager;

import app.historyManager.HistoryManager;
import app.historyManager.InMemoryHistoryManager;

public class Managers {

    private Managers() {
    }

    public static TaskManager getDefault() {
        HistoryManager historyManager = getDefaultHistory();
        return new InMemoryTaskManager(historyManager);
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        return inMemoryHistoryManager;
    }

}