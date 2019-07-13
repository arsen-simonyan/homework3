package am.newway.homework3;

public enum TaskType
{
    IMP("important"),
    ORD("ordinary"),
    UNI("unimportant");

    private String type;
    TaskType(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }

    public static String toString(TaskType tsk)
    {
        return tsk.get();
    }
}
