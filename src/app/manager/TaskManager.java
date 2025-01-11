package app.manager;

import app.tasks.Epic;
import app.enumeration.Status;
import app.tasks.Subtask;
import app.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    Task createTask(Task task);

    Epic createEpic(Epic epic);

    Subtask createSubtask(Subtask subtask);

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    Task getTaskById(Integer id);

    Task getEpicById(Integer id);

    Task getSubtaskById(Integer id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    Task deleteTaskById(Integer id);

    Epic deleteEpicById(Integer id);

    Subtask deleteSubtaskById(Integer id);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    Status chekStatus(Integer id);

    List<Task> getHistory();
}