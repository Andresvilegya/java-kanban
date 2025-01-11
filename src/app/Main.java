package app;

import app.enumeration.Status;
import app.manager.Managers;
import app.manager.TaskManager;
import app.tasks.Epic;
import app.tasks.Subtask;
import app.tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        Task newTask1 = new Task("Задача 1", "Сдать спринт 4.");
        System.out.println(taskManager.createTask(newTask1));
        Task newTask2 = new Task("Задача 2", "Сдать спринт 5.");
        taskManager.createTask(newTask2);
        Epic newEpic1 = new Epic(null,"Эпик 1", "Закрыть хвосты.", Status.NEW);
        taskManager.createEpic(newEpic1);
        Subtask newSubtask1 = new Subtask(null,"Подзадача 1", "Сдать спринт 4.", Status.NEW, 3);
        taskManager.createSubtask(newSubtask1);
        Subtask newSubtask2 = new Subtask(null,"Подзадача 2", "Сдать спринт 5.", Status.NEW, 3);
        taskManager.createSubtask(newSubtask2);
        Epic newEpic2 = new Epic(null,"Эпик 2", "Повторить материал.", Status.NEW);
        taskManager.createEpic(newEpic2);
        Subtask newSubtask3 = new Subtask(null,"Подзадача 1", "Повторить теорию.", Status.NEW, 6);
        taskManager.createSubtask(newSubtask3);
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(taskManager.getSubtaskById(4));
        taskManager.deleteTaskById(1);
        System.out.println(taskManager.getAllTasks());
        taskManager.deleteEpicById(6);
        System.out.println(taskManager.getAllSubtasks());
        Task updTask2 = new Task("Задача 2+", "Пересдать спринт 5.");
        taskManager.updateTask(updTask2);
        Subtask updSubtask4 = new Subtask(4,"Подзадача 1", "Повторить теорию.", Status.IN_PROGRESS, 3);
        taskManager.updateSubtask(updSubtask4);
        System.out.println(taskManager.getEpicById(3));
        Epic updEpic1 = new Epic(3,"Эпик 1", "Закрыть хвосты.", Status.DONE);
        taskManager.updateEpic(updEpic1);
        System.out.println(taskManager.getEpicById(3));
        taskManager.deleteSubtaskById(4);
        taskManager.deleteSubtaskById(5);
        //taskManager.deleteEpicById(3);
        System.out.println(taskManager.getSubtaskById(4));
        //taskManager.deleteAllTasks();
        //taskManager.deleteAllSubtasks();
       // taskManager.deleteAllEpics();
        Subtask newSubtask4 = new Subtask(1,"Подзадача 8", "Сдать спринт 4.", Status.NEW, 1);
        System.out.println(taskManager.createSubtask(newSubtask4));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getHistory());

    }
}