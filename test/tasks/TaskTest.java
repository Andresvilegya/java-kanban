package tasks;

import app.manager.Managers;
import app.manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private TaskManager taskManager;

    @BeforeEach
    public void init(){
        taskManager = Managers.getDefault();
    }


    @Test
    void testToString() {
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }
}