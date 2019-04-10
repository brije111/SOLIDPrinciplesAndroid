package com.example.solidprinciplesandroid.ocp;

public class TimeOfDayGreeting {
    private String timeOfDay;
    /*
     * Every time this method is called it will
     * called an if else logic, which is in violation of the
     * OCP principle.
     */
    public String getGreetingFromTimeOfDay() {
        if (this.timeOfDay.equals("Morning")) {
            return "Good Morning, sir.";
        }
        else if (this.timeOfDay.equals("Afternoon")) {
            return "Good Afternoon, sir.";
        }
        else if (this.timeOfDay.equals("Evening")) {
            return "Good Evening, sir.";
        }
        else {
            return "Good Night, sir.";
        }
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}

/* Create an interface called TimeOfDay and let the Morning, Afternoon,
 * Evening classes implement this interface.
 * This interface can then be called inside the TimeOfDayGreeting class.
 * This means the getGreetingFromTimeOfDay() method need not handle
 * any logic
 */

 class TimeOfDayGreetingUpdated {
    private TimeOfDay timeOfDay;

    public TimeOfDayGreetingUpdated(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getGreetingFromTimeOfDay() {
        return this.timeOfDay.greet();
    }
}


 interface TimeOfDay {
    public String greet();
}

/*  Morning class  */
 class Morning implements TimeOfDay {
    public String greet() {
        return "Good morning, sir.";
    }
}

/*  Afternoon class  */
 class Afternoon implements TimeOfDay {
    public String greet() {
        return "Good afternoon, sir.";
    }
}

/*  Evening class  */
 class Evening implements TimeOfDay {
    public String greet() {
        return "Good evening, sir.";
    }
}

/*  Night class  */
 class Night implements TimeOfDay {
    public String greet() {
        return "Good night, sir.";
    }
}
