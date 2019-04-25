# SOLID Principles Android

![build pass](https://travis-ci.org/brije111/SOLIDPrinciplesAndroid.svg?branch=master)


SOLID is one of the most important acronyms in the world of Object Oriented Programming.

The importance of SOLID comes from the fact that it is practically impossible to write a &quot;clean&quot; code if you don&#39;t know what distinguishes a &quot;clean&quot; code from a &quot;dirty&quot; one.

SOLID principle are not rules or laws or perfect truth. It is a good principle or advice in order to write &quot;clean&quot; code.

Developed by American software engineer &amp; instructor Robert C Martin (also known as Uncle Bob) in his 2000 paper &quot;Design principles &amp; design patterns&quot;. He is also the author of the &quot;Clean Code&quot;.

It was Michael Feathers (the author of &quot;Working Effectively with Legacy Code&quot;) who &quot;acronymized&quot; these principles to SOLID.


### 1. Single Responsibility Principle (SRP)

- **A class should have one and only one reason to change**
- In SRP responsibility is &quot;reason to change&quot;.
- SRP looks simple on the first glance, but it is, probably, the most difficult SOLID principle to follow in practice.
- Following SRP is difficult because &quot;reasons to change&quot; become certain only in the future, when requirements actually change. At design time, all we can do is estimate which requirements are likely to change and which are stable. In other words, following SRP inevitably involves some amount of prophetic work.
- In addition to being the most difficult to implement, SRP is also the most fundamental SOLID principle. The more the design violates SRP, the less the gain from application of other SOLID principles will be.
- To know whether a class follow SRP ask &quot;What that class does?&quot; If you have to use the conjunction &quot;and&quot; or &quot;or&quot; then it is likely that class don&#39;t follow SRP

### What is meant by “Responsibility” though?
Uncle Bob quote - 
> In the context of the Single Responsibility Principle (SRP) we define a responsibility as “a reason for change”. If you can think of more than one motive for changing a class, then that class has more than one responsibility.

**Benefits**

- Easier to change
- Easier to understand
- More reusable
- Easier to test



**Example 1**
```
package srp;

public class Employee {
	double calculatePay() {
		//business logic for calculating payment
		return 0;
	}
	
	Employee save(Employee emp) {
		//business logic for storing employee information
		return emp;
	}
}
/*
 * So this call has 2 responsibility, So this doesn't follow the SRP
 * The solution is there should be two classes one for each responsibility
 */
class EmployeePay{
	double calculatePay() {
		//business logic for calculating payment
		return 0;
	}
}
class EmployeeRepo{
	Employee save(Employee emp) {
		//business logic for storing employee information
		return emp;
	}
}
```



**Example 2**
```
package srp;

import java.util.List;

public class ProductRepo {
	Database initDb() {
		//open database connection here
		return null;
	}
	List<Product> getProducts(Database database){
		//fetch product data from database
		return null;
	}
	boolean save(List<Product> productList) {
		//save the product list data in external file
		return false;
	}
}
class Database{}
class Product{}
/*
 * The above class handles 3 operations(responsibilities) i.e. initializing the database, 
 * fetching the data and writing that data in external file.
 * So we can make 3 classes for each operation in order to apply SRP.
 */

class DatabaseConnectionUtils{
	Database initDb() {
		//open database connection here
		return null;
	}
}

class DatabaseUtils{
	List<Product> getProducts(Database database){
		//fetch product data from database
		return null;
	}
}

class FileUtils{
	boolean save(List<Product> productList) {
		//save the product list data in external file
		return false;
	}
}
```

**Example 3**

```
package srp;

public interface Modem {
	public void dial(String phone);
	public void hangup();
	
	public void send(char ch);
	public char receive();
}
/*
 * This interface got two responsibility, one is connection management 
 * and the other is data communication
 * We can make 2 interfaces here to achieve SRP
*/
interface ModemConnection{
	public void dial(String phone);
	public void hangup();
}

interface ModemDataManagement{
	public void send(char ch);
	public char receive();
}
```
**Example 4 - Android Recycler View Adapter**

```
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Person> personList;

    public RecyclerViewAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //creates view for list items
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //sets data on created view
        Person person = personList.get(i);
        viewHolder.tv_name.setText(person.name);
        viewHolder.tv_age.setText(String.valueOf(person.age));

        /*
        Here this violates SRP as the logic of address concatenation resides in Adapter class.
        To solve this we need to extract this logic in another class.
         */
        String address = person.address.addressLine1+", "+
                person.address.addressLine2+", "+
                person.address.addressLine3+", "+
                person.address.city+" "+ person.address.state+" "+ person.address.country+" ";

        viewHolder.tv_address.setText(address);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    //Handles ViewHolder pattern implementation
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_age;
        TextView tv_address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /*
    By moving that address concatenation logic, this class will be following SRP. All Looks good.
    Now let's see it with a different mindset.
    I can see this class got 3 responsibility.
    1. creates view for list items
    2. sets data on created view
    3. Handles ViewHolder pattern implementation
    So clearly this don't follow SRP.
    So what we should do for make it to follow SRP,
    split the methods in 3 classes so that all 3 classes will get their own one and only one responsibility.
    Well It's not that easy. Here I'll recall the concept of "reason to change".
    Generally what a adapter do? It show provided data to a view.
    So We can say here Adapter has only one reason to change the class i.e. when we get to change in view.
    Nothing else will enforce to change the adapter. So I can say it is following the SRP in its current structure.
    Also all the methods in adapter are tightly coupled to provide the adapter functionality.
    Refactoring them further will make the code more complex.
     */
}
```

**Example 5 - Android's Context class**

```
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
```

### 2. Open Close Principle (OCP)

- **Software entities (classes, modules, functions, etc) should be open for extension, but closed for modification**
- Means if the class A is written by the developer AA, and if the developer BB wants some modification on that then developer BB should be easily do that by extending class A, but not by modifying class A

**Example 1**
```
public class TimeOfDayGreeting {
    private String timeOfDay;
    /*
     * Every time this method is called it will
     * called an if else logic, which is in violation of the
     * OCP principle.
     */
    public String getGreetingFromTimeOfDay() {
        if (this.timeOfDay == "Morning") {
            return "Good Morning, sir.";
        }
        else if (this.timeOfDay == "Afternoon") {
            return "Good Afternoon, sir.";
        }
        else if (this.timeOfDay == "Evening") {
            return "Good Evening, sir.";
        }
        else {
            return "Good Night, sir.";
        }
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /* Create an interface called TimeOfDay and let the Morning, Afternoon,
     * Evening classes implement this interface.
     * This interface can then be called inside the TimeOfDayGreeting class.
     * This means the getGreetingFromTimeOfDay() method need not handle
     * any logic
     */
    public class TimeOfDayGreetingUpdated {
        private TimeOfDay timeOfDay;

        public TimeOfDayGreetingUpdated(TimeOfDay timeOfDay) {
            this.timeOfDay = timeOfDay;
        }

        public String getGreetingFromTimeOfDay() {
            return this.timeOfDay.greet();
        }
    }


    public interface TimeOfDay {
        public String greet();
    }

    /*  Morning class  */
    public class Morning implements TimeOfDay {
        public String greet() {
            return "Good morning, sir.";
        }
    }

    /*  Afternoon class  */
    public class Afternoon implements TimeOfDay {
        public String greet() {
            return "Good afternoon, sir.";
        }
    }

    /*  Evening class  */
    public class Evening implements TimeOfDay {
        public String greet() {
            return "Good evening, sir.";
        }
    }

    /*  Night class  */
    public class Night implements TimeOfDay {
        public String greet() {
            return "Good night, sir.";
        }
    }
}
```

**Example 2**

```
/*
    This class got code for calculating area according to Shape.
    There are many conditions in the code for getting shape specific area.
    If there is a change in client requirement & now we need to calculate area of Circle shape too.
    Now we need to modify the Area manager code to write logic of calculating area of new shape circle.
    This is clearly a violation of OCP.
 */

public class AreaManager {
    public double calculateArea(ArrayList<Object>... shapes) {
        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle rect = (Rectangle)shape;
                area += (rect.length * rect.height);
            } else if (shape instanceof Circle) {
                Circle circle = (Circle)shape;
                area += (circle.radius * circle.radius * Math.PI);
            } else {
                throw new RuntimeException("Shape not supported");
            }
        }
        return area;
    }
    public class Rectangle {
        private double length;
        private double height;
    }
    public class Circle {
        private double radius;
    }
}

/*
    To Correct this problem, we made a new interface Shape, containing a method "getArea()".
    Every shape will implement this interface & the logic of area calculation will be written in getArea() method.
    So in case a new shape will be added in future, there will be no change in AreaManager class.
    Now it is following the OCP.
 */

 interface Shape {
    double getArea();
}

 class Rectangle implements Shape {
    private double length;
    private double height;

    @Override
    public double getArea() {
        return (length * height);
    }
}

 class Circle implements Shape {
    private double radius;

    @Override
    public double getArea() {
        return (radius * radius * Math.PI);
    }
}

 class AreaManagerUpdated {
    public double calculateArea(ArrayList<Shape> shapes) {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }
}
```

**Example 3 - Android's TextView Class**

Button, CheckedTextView, Chronometer, DigitalClock, EditText, TextClock directly
and AutoCompleteTextView, CheckBox, CompoundButton, ExtractEditText, MultiAutoCompleteTextView, RadioButton, Switch, ToggleButton indirectly extends
TextView class and change or update some behavior of Android's default text view.
So here we can say this class is following OCP.

[image text view class](image/text_view_class.PNG)

**Example 4 - Android's ViewGroup Class**

Same as text view class, AbsoluteLayout, AdapterView<T extends Adapter>, FragmentBreadCrumbs, FrameLayout, GridLayout, LinearLayout, RelativeLayout, SlidingDrawer, Toolbar, TvView are the direct subclasses of View Group class. So we can say ViewGroup class also follow OCP.

[image view group class](image/view_group_class.PNG)

### 3. Liskov Substitution Principle (LSP)

>Child classes should never break the parent class’ type definitions.  

In other words

>Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.

- A subclass should override the parent class’ methods in a way that does not break functionality from a client’s point of view. Here is a simple example to demonstrate the concept.
- A Behavioral Notion of Subtyping (1994) by Barbara H. Liskov. [link](http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.39.1223)  

[Example 1](app\src\main\java\com\example\solidprinciplesandroid\lsp\LSP.java)

[Example 2](app\src\main\java\com\example\solidprinciplesandroid\lsp\AndroidExample.java)
 
### 4. Interface Segregation Principle (ISP)
 
 >The interface-segregation principle (ISP) states that no client should be forced to depend on methods it does not use.
 
 In other words
 
 > Make fine grained interfaces that are client-specific.
 
 Again in other words
 
 >Many client-specific interfaces are better than one general purpose interface
 
 - This principle states that once an interface becomes too fat, it needs to be split into smaller interfaces so that client of the interface will only know about the methods that pertain to them. As you know, the Android View class is the root superclass for all Android views. You name it, if it’s a Button, the root superclass is View.
 
 [Example](app\src\main\java\com\example\solidprinciplesandroid\isp\ISP.java)
 
 - Android's ViewPager.OnPageChangeListener got three methods
 ```
 interface ViewPager.OnPageChangeListener{
            void onPageScrollStateChanged();
            void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
            void onPageSelected(int position);
        }
 ```
 ```
 interface TextWatcher {
            void beforeTextChanged(CharSequence s, int start, int count, int after);
            void onTextChanged(CharSequence s, int start, int before, int count);
            void afterTextChanged(Editable s);
        }
 ```
 So this need to be segregated into three interfaces to follow ISP. No It's not. These methods are all very specific to the interface and the client will most likely want to interact with them, therefore packaging them together in the same interface is the right thing to do
 
### Reference
 
 **1.** [https://academy.realm.io/posts/donn-felker-solid-part-1/](https://academy.realm.io/posts/donn-felker-solid-part-1/)  
 **2.** [https://android.jlelse.eu/single-responsibility-principle-and-context-60e39a28e5bd](https://android.jlelse.eu/single-responsibility-principle-and-context-60e39a28e5bd)  
 **3.** [https://medium.com/mindorks/solid-principles-explained-with-examples-79d1ce114ace](https://medium.com/mindorks/solid-principles-explained-with-examples-79d1ce114ace)  
 **4.** [https://developer.android.com/reference/android/widget/TextView](https://developer.android.com/reference/android/widget/TextView)  
 **5.** [https://developer.android.com/reference/android/view/ViewGroup.html](https://developer.android.com/reference/android/view/ViewGroup.html)
