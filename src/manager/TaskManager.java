package manager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int counter = 1;

    public Task createTask(Task task) {
        int id = nextId();
        task.setId(id);
        Task newTask = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        tasks.put(newTask.getId(), newTask);
        return task;
    }

    public Epic createEpic(Epic epic) {
        int id = nextId();
        epic.setId(id);
        Epic newEpic = new Epic(epic.getId(), epic.getName(), epic.getDescription(), epic.getStatus());
        epics.put(newEpic.getId(), newEpic);
        return epic;
    }

    public Subtask createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            int id = nextId();
            subtask.setId(id);
            Subtask newSubtask = new Subtask(subtask.getId(), subtask.getName(), subtask.getDescription(), subtask.getStatus(), subtask.getEpicId());
            subtasks.put(newSubtask.getId(), newSubtask);
            epics.get(subtask.getEpicId()).getSubtaskIds().add(id);
            epics.get(subtask.getEpicId()).setStatus(chekStatus(subtask.getEpicId()));
            return subtask;
        } else {
            return null;
        }
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task findTaskById(Integer Id) {
        return tasks.get(Id);
    }

    public Task findEpicById(Integer Id) {
        return epics.get(Id);
    }

    public Task findSubtaskById(Integer Id) {
        return subtasks.get(Id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (int epicId  : epics.keySet() ) {
            epics.get(epicId).getSubtaskIds().clear();
            epics.get(epicId).setStatus(Status.NEW);
        }
    }

    public Task deleteTaskById(Integer id) {
        Task task = tasks.remove(id);
        return task;
    }

    public Epic deleteEpicById(Integer id) {
        for (Integer subtaskId : epics.get(id).getSubtaskIds()) {
            subtasks.remove(subtaskId);
        }
        Epic epic = epics.remove(id);
        return epic;
    }

    public Subtask deleteSubtaskById(Integer id) {
        int epicId = subtasks.get(id).getEpicId();
        Subtask subtask = subtasks.remove(id);
        epics.get(epicId).getSubtaskIds().remove(id);
        epics.get(epicId).setStatus(chekStatus(epicId));
        return subtask;
        }

    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        } else {
            return null;
        }
    }

    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.get(epic.getId()).setName(epic.getName());
            epics.get(epic.getId()).setDescription(epic.getDescription());
            return epic;
        } else return null;
    }

    public Subtask updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
        int epicId = subtask.getEpicId();
        subtasks.put(subtask.getId(), subtask);
        epics.get(epicId).setStatus(chekStatus(epicId));
        return subtask;
    } else return null;
    }

    private int nextId() {
        return counter++;
    }

    private Status chekStatus(Integer id) {
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
}