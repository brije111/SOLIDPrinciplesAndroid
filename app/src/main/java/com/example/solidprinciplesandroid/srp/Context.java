package com.example.solidprinciplesandroid.srp;

import android.app.Activity;
import android.content.SharedPreferences;

public class Context /*extends Context*/{

    /*
    I assume this class as android's default Context class
     */

    public Object getSystemService(String name) {
        //for getting services related to harware dependency, like TelephonyManager, AudioService etc
        return null;
    }


    public int checkSelfPermission(String permission) {
        // for checking permissions at runtime like READ_WRITE_PERMISSION for managing files on device
        return 0;
    }


    public SharedPreferences getSharedPreferences(String name, int mode) {
        //geting stored key-value paired database
        return null;
    }
    /*
        Here this context class got 3 responsibility.
        If we split these 3 responsibility in 3 respective classes, this will follow the SRP
        But this doesn't end up here. The android's default Context class got 4000+ lines of code including 1000+ methods.
        Refactoring all those methods in same number of classes is not a good deal.
        Context is a kind of god object for android, and It is the atmost parent of 3 basic components of android i.e. Application, Activity, Service.
        All the methods in contects class collectively provide a environment for creating and running these components. So from the other point of view we can say
        context class got one responsibility to provide environment to the android's basic component to run. So I think It's somewhere following the SRP.

        Conclusion - At the end I'll say the SRP is a principlae that is hard to implement because of it's dynamic nature to identify the responsibility.
        It's not that straight, number of functions = number of responsibility
        The identification of responsibility depends on project specific use cases. It's you who will decide the level of SRP you gonna follow.
     */

}
