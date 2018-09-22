package com.appstuff.ben.agenda;

public class Reference {
    /* This class exists to act as a form of pointer in Java.  Java is explicitly pass-by-value, so
    any primitive data types passed into a function have their values passes.  Any objects passed
    have their references passes, and the objects properties can be accessed and modified through
    that reference.  However, if you want to change the reference completely, then the original
    object reference outside the function is unchanged.  That's where this class comes in.

    This class contains a single property, an Object.  If a Reference object is passed to a
    function, then the inner Object can be modified or replaced at will, with these changes being
    permanent outside the function as well.  It's not a permanent solution, so use wisely.
     */

    public Object obj;

    public Reference(Object object) {
        this.obj = object;
    }
}
