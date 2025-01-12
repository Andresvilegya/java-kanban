package app.historyManager;

import app.tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private LinkedList<Task> history = new LinkedList<>();
    private final int SIZE_HISORY = 10;

    @Override
    public void addToHistory(Task task) {
        if (history.size() >= SIZE_HISORY)  history.removeFirst();
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
         List<Task> viewHistory  = new ArrayList<Task>(history);
         return viewHistory;
    }
}