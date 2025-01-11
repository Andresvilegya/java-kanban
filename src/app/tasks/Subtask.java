package app.tasks;

import app.enumeration.Status;

public class Subtask extends Task {
    private Integer  epicId;

    public Subtask(Integer id, String name, String description, Status status, Integer epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public Subtask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    @Override // переопределяем toString
    public String toString() {
        return "id:" + getId() + ", name:" + getName() + ", status:" + getStatus() + ", epicId:" + epicId;
    }
}