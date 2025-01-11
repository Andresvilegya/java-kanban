package app.manager;
import app.HistoryManager.HistoryManager;
import app.tasks.Epic;
import app.enumeration.Status;
import app.tasks.Subtask;
import app.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int counter = 1;
    private HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }


    @Override
    public Task createTask(Task task) {
        int id = nextId();
        task.setId(id);
        task.setStatus(Status.NEW);
        Task newTask = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        tasks.put(newTask.getId(), newTask);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        int id = nextId();
        epic.setId(id);
        epic.setStatus(Status.NEW);
        Epic newEpic = new Epic(epic.getId(), epic.getName(), epic.getDescription(), epic.getStatus());
        epics.put(newEpic.getId(), newEpic);
        return epic;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            int id = nextId();
            subtask.setId(id);
            subtask.setStatus(Status.NEW);
            Subtask newSubtask = new Subtask(subtask.getId(), subtask.getName(), subtask.getDescription(), subtask.getStatus(), subtask.getEpicId());
            subtasks.put(newSubtask.getId(), newSubtask);
            epics.get(subtask.getEpicId()).getSubtaskIds().add(id);
            epics.get(subtask.getEpicId()).setStatus(chekStatus(subtask.getEpicId()));
            return subtask;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTaskById(Integer id) {
        if (!tasks.containsKey(id)) return null;
        Task task = tasks.get(id);
        Task taskForHistory = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        historyManager.addToHistory(taskForHistory);
        return tasks.get(id);
    }

    @Override
    public Task getEpicById(Integer id) {
        if (!epics.containsKey(id)) return null;
        Task epic = epics.get(id);
        Task epicForHistory = new Task(epic.getId(), epic.getName(), epic.getDescription(), epic.getStatus());
        historyManager.addToHistory(epicForHistory);
        return epics.get(id);
    }

    @Override
    public Task getSubtaskById(Integer id) {
        if (!subtasks.containsKey(id)) return null;
        Task subtask = subtasks.get(id);
        Task subtaskForHistory = new Task(subtask.getId(), subtask.getName(), subtask.getDescription(), subtask.getStatus());
        historyManager.addToHistory(subtaskForHistory);
        return subtasks.get(id);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (int epicId  : epics.keySet() ) {
            epics.get(epicId).getSubtaskIds().clear();
            epics.get(epicId).setStatus(Status.NEW);
        }
    }

    @Override
    public Task deleteTaskById(Integer id) {
        Task task = tasks.remove(id);
        return task;
    }

    @Override
    public Epic deleteEpicById(Integer id) {
        for (Integer subtaskId : epics.get(id).getSubtaskIds()) {
            subtasks.remove(subtaskId);
        }
        Epic epic = epics.remove(id);
        return epic;
    }

    @Override
    public Subtask deleteSubtaskById(Integer id) {
        int epicId = subtasks.get(id).getEpicId();
        Subtask subtask = subtasks.remove(id);
        epics.get(epicId).getSubtaskIds().remove(id);
        epics.get(epicId).setStatus(chekStatus(epicId));
        return subtask;
        }

    @Override
    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        } else {
            return null;
        }
    }

    @Override
    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.get(epic.getId()).setName(epic.getName());
            epics.get(epic.getId()).setDescription(epic.getDescription());
            return epic;
        } else return null;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
        int epicId = subtask.getEpicId();
        subtasks.put(subtask.getId(), subtask);
        epics.get(epicId).setStatus(chekStatus(epicId));
        return subtask;
    } else return null;
    }

    @Override
    public Status chekStatus(Integer id) {
        int numberNew = 0;
        int numberDone = 0;
        int numberInProgress = 0;
        for (Integer subtaskId : epics.get(id).getSubtaskIds()) {
            switch (subtasks.get(subtaskId).getStatus()) {
                case NEW:
                    numberNew += 1;
                    break;
                case DONE:
                    numberDone += 1;
                    break;
                case IN_PROGRESS:
                    numberInProgress += 1;
                    break;
            }
        }
        Status status;
        if (numberNew >= 0 && numberDone == 0 && numberInProgress == 0) {
            status = Status.NEW;
        } else if (numberNew == 0 & numberDone > 0 && numberInProgress == 0) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }
        return status;
    }

    public List<Task> getHistory() {
        return new ArrayList<>(historyManager.getHistory());
    }

    private int nextId() {
        return counter++;
    }
}