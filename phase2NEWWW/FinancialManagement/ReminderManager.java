package FinancialManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderManager {
    private List<Reminder> allRemiders = new ArrayList<>(); 

    public void addReminder (String msg, LocalDateTime due) {
        allRemiders.add(new Reminder(msg, due));
    }

    public void manageSendingReminders () {
        for (Reminder rem : allRemiders ) {
            rem.sendReminders();
        }
    }


    public static class Reminder {
    private String msgInfo;
    private LocalDateTime dueDate;
    private boolean isDone;

    public Reminder (String msg, LocalDateTime due) {
        this.msgInfo = msg;
        this.dueDate = due;
        this.isDone = false;
    }

    public boolean isExpired () {
        return !isDone && LocalDateTime.now().isAfter(dueDate);
    }

    public void sendReminders () {
        if (isExpired()) {
            System.out.println("There is a reminder! \n [Reminder]: " + msgInfo + " (Due: " + dueDate + ")");
            isDone = true;
        }
    }
}
}
