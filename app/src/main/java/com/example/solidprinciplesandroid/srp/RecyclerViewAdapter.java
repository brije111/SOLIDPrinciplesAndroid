package com.example.solidprinciplesandroid.srp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 51778499 on 05,April,2019
 * Hcl Technologies,
 * India.
 */
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

class Person{
    String name;
    int age;
    Address address;
}
class Address{
    String addressLine1;
    String addressLine2;
    String addressLine3;
    String city;
    String state;
    String country;
}
