package financialManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of reminders and handles the sending of expired reminders.
 * This class allows adding new reminders and checking whether they are due to be sent.
 */
public class ReminderManager {
    private List<Reminder> allRemiders = new ArrayList<>(); 

    /**
     * Adds a new reminder with a specified message and due date.
     *
     * @param msg The reminder message.
     * @param due The due date and time for the reminder.
     */
    public void addReminder (String msg, LocalDateTime due) {
        allRemiders.add(new Reminder(msg, due));
    }

    /**
     * Checks each reminder in the list and sends those that are due.
     */
    public void manageSendingReminders () {
        for (Reminder rem : allRemiders ) {
            rem.sendReminders();
        }
    }

    /**
     * Represents a single reminder with a message, due date, and completion status.
     * Provides functionality for checking expiration and sending reminders.
     */
    public static class Reminder {
        private String msgInfo;
        private LocalDateTime dueDate;
        private boolean isDone;

        /**
         * Constructs a new Reminder with a specified message and due date.
         *
         * @param msg The reminder message.
         * @param due The due date and time for the reminder.
         */
        public Reminder (String msg, LocalDateTime due) {
            this.msgInfo = msg;
            this.dueDate = due;
            this.isDone = false;
        }

        /**
         * Checks whether the reminder has expired, i.e., whether the due date has passed and the reminder has not been marked as done.
         *
         * @return true if the reminder is expired, false otherwise.
         */
        public boolean isExpired () {
            return !isDone && LocalDateTime.now().isAfter(dueDate);
        }

        /**
         * Sends the reminder if it has expired, marking it as done.
         */
        public void sendReminders () {
            if (isExpired()) {
                System.out.println("There is a reminder! \n [Reminder]: " + msgInfo + " (Due: " + dueDate + ")");
                isDone = true;
            }
        }
    }
}
