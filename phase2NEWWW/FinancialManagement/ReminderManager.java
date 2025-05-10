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
}
