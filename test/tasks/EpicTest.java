package tasks;

import app.manager.Managers;
import app.manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private TaskManager taskManager;

    @BeforeEach
    public void init(){
        taskManager = Managers.getDefault();
    }

    @Test
    void getSubtaskIds() {
    }

    @Test
    void setSubtaskIds() {
    }
}