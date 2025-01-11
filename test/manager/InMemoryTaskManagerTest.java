package manager;

import app.enumeration.Status;
import app.manager.Managers;
import app.manager.TaskManager;
import app.tasks.Epic;
import app.tasks.Subtask;
import app.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void init(){
        taskManager = Managers.getDefault();
    }

    @Test
    void createTaskTest() {
        String description = "Написать тесты";
        String name = "Сделать ТЗ";
        Task task = new Task(name, description);

        taskManager.createTask(task);

        Task actualTask = taskManager.getTaskById(task.getId());
        Assertions.assertNotNull(actualTask.getId(), "Задача не найдена.");
        Assertions.assertEquals(actualTask.getStatus(), Status.NEW, "Статусы не совпадают.");
        Assertions.assertEquals(actualTask.getDescription(), description, "Описания не совпадают.");
        Assertions.assertEquals(actualTask.getName(), name, "Названия не совпадают.");
        Assertions.assertNotNull(taskManager.getAllTasks(), "Задачи не возвращаются.");

    }

    @Test
    void createEpicTest() {
        String description = "Написать тесты";
        String name = "Эпик ТЗ";
        Epic epic = new Epic(name, description);

        taskManager.createEpic(epic);

        Epic actualEpic = (Epic) taskManager.getEpicById(epic.getId());
        Assertions.assertNotNull(actualEpic.getId(), "Задача не найдена.");
        Assertions.assertEquals(actualEpic.getStatus(), Status.NEW, "Статусы не совпадают.");
        Assertions.assertEquals(actualEpic.getDescription(), description, "Описания не совпадают.");
        Assertions.assertEquals(actualEpic.getName(), name, "Названия не совпадают.");
        Assertions.assertNotNull(taskManager.getAllEpics(), "Эпики не возвращаются.");
    }

    @Test
    void createSubtaskTest() {
        String description = "Написать тесты";
        String name = "Эпик ТЗ";
        Epic epic = new Epic(name, description);
        Integer epicId = taskManager.createEpic(epic).getId();
        Subtask subtask = new Subtask(name, description,  epicId);

        taskManager.createSubtask(subtask);

        Subtask actualSubtask = (Subtask) taskManager.getSubtaskById(subtask.getId());
        Assertions.assertNotNull(actualSubtask.getId(), "Задача не найдена.");
        Assertions.assertEquals(actualSubtask.getStatus(), Status.NEW, "Статусы не совпадают.");
        Assertions.assertEquals(actualSubtask.getDescription(), description, "Описания не совпадают.");
        Assertions.assertEquals(actualSubtask.getName(), name, "Названия не совпадают.");
        Assertions.assertNotNull(taskManager.getAllSubtasks(), "Подзадачи не возвращаются.");

    }
/*
    @Test
    void getAllTasks() {
    }

    @Test
    void getAllEpics() {
    }

    @Test
    void getAllSubtasks() {
    }

    @Test
    void findTaskById() {
    }

    @Test
    void findEpicById() {
    }

    @Test
    void findSubtaskById() {
    }

    @Test
    void deleteAllTasks() {
    }

    @Test
    void deleteAllEpics() {
    }

    @Test
    void deleteAllSubtasks() {
    }

    @Test
    void deleteTaskById() {
    }

    @Test
    void deleteEpicById() {
    }

    @Test
    void deleteSubtaskById() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void updateEpic() {
    }

    @Test
    void updateSubtask() {
    }

    @Test
    void chekStatus() {
    }

    @Test
    void chekStatus() {
    }
    */

    @Test
    void taskInHistoryListShouldNotBeUpdatedAfterTaskUpdete() {
        String description = "Написать тесты";
        String name = "Сделать ТЗ";
        Task task = new Task(name, description);

        taskManager.createTask(task);
        taskManager.getTaskById(task.getId());
        Task taskInHistory = taskManager.getHistory().get(0);
        Status statusInHistoryBeforUpdate = taskInHistory.getStatus();

        task.setStatus(Status.DONE);
        taskManager.updateTask(task);

        Task taskInHistoryAfterUpdate = taskManager.getHistory().get(0);
        Assertions.assertEquals(statusInHistoryBeforUpdate, taskInHistoryAfterUpdate.getStatus(), "Статусы не совпадают.");


    }
}