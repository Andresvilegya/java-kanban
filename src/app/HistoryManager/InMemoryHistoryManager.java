package app.HistoryManager;

import app.tasks.Task;
import app.manager.InMemoryTaskManager ;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private ArrayList<Task> history = new ArrayList<>();
    private int sizeHistory = 10;

    @Override
    public void addToHistory(Task task) {
        if (history.size() >= sizeHistory)  history.removeFirst();


        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}