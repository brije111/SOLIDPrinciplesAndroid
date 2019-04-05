# SOLID Principles Android


SOLID is one of the most important acronyms in the world of Object Oriented Programming.

The importance of SOLID comes from the fact that it is practically impossible to write a &quot;clean&quot; code if you don&#39;t know what distinguishes a &quot;clean&quot; code from a &quot;dirty&quot; one.

SOLID principle are not rules or laws or perfect truth. It is a good principle or advice in order to write &quot;clean&quot; code.

Developed by American software engineer &amp; instructor Robert C Martin (also known as Uncle Bob) in his 2000 paper &quot;Design principles &amp; design patterns&quot;

It was Michael Feathers (the author of &quot;Working Effectively with Legacy Code&quot;) who &quot;acronymized&quot; these principles to SOLID.


### 1. Single Responsibility Principle (SRP)

- **A class should have one and only one reason to change**
- In SRP responsibility is &quot;reason to change&quot;.
- SRP looks simple on the first glance, but it is, probably, the most difficult SOLID principle to follow in practice.
- Following SRP is difficult because &quot;reasons to change&quot; become certain only in the future, when requirements actually change. At design time, all we can do is estimate which requirements are likely to change and which are stable. In other words, following SRP inevitably involves some amount of prophetic work.
- In addition to being the most difficult to implement, SRP is also the most fundamental SOLID principle. The more the design violates SRP, the less the gain from application of other SOLID principles will be.
- To know whether a class follow SRP ask &quot;What that class does?&quot; If you have to use the conjunction &quot;and&quot; or &quot;or&quot; the it is likely that class don&#39;t follow SRP

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

 ### Reference
 
 **1.** [https://academy.realm.io/posts/donn-felker-solid-part-1/](https://academy.realm.io/posts/donn-felker-solid-part-1/)
 
 **2.** [https://android.jlelse.eu/single-responsibility-principle-and-context-60e39a28e5bd](https://android.jlelse.eu/single-responsibility-principle-and-context-60e39a28e5bd)
