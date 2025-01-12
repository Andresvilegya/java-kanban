package historyManager;

import app.manager.Managers;
import app.manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryHistoryManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void init(){
        taskManager = Managers.getDefault();
    }


    @Test
    void add() {
    }

    @Test
    void getHistory() {
    }
}