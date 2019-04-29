# SOLID Principles Android

![build pass](https://travis-ci.org/brije111/SOLIDPrinciplesAndroid.svg?branch=master)


SOLID is one of the most important acronyms in the world of Object Oriented Programming.

The importance of SOLID comes from the fact that it is practically impossible to write a &quot;clean&quot; code if you don&#39;t know what distinguishes a &quot;clean&quot; code from a &quot;dirty&quot; one.

SOLID principle are not rules or laws or perfect truth. It is a good principle or advice in order to write &quot;clean&quot; code.

Developed by American software engineer &amp; instructor Robert C Martin (also known as Uncle Bob) in his 2000 paper &quot;Design principles &amp; design patterns&quot;. He is also the author of the &quot;Clean Code&quot;.

It was Michael Feathers (the author of &quot;Working Effectively with Legacy Code&quot;) who &quot;acronymized&quot; these principles to SOLID.

**Benefits**

- Easier to change
- Easier to understand
- More reusable
- Easier to test

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

[Example 1](app/src/main/java/com/example/solidprinciplesandroid/srp/Employee.java)

[Example 1](app/src/main/java/com/example/solidprinciplesandroid/srp/ProductRepo.java)

[Example 1](app/src/main/java/com/example/solidprinciplesandroid/srp/Modem.java)

[Example 4 - Android Recycler View Adapter](app/src/main/java/com/example/solidprinciplesandroid/srp/RecyclerViewAdapter.java)

[Example 5 - Android's Context class](app/src/main/java/com/example/solidprinciplesandroid/lsp/TimeOfDayGreeting.java)

### 2. Open Close Principle (OCP)

- **Software entities (classes, modules, functions, etc) should be open for extension, but closed for modification**
- Means if the class A is written by the developer AA, and if the developer BB wants some modification on that then developer BB should be easily do that by extending class A, but not by modifying class A

[Example 1](app/src/main/java/com/example/solidprinciplesandroid/lsp/TimeOfDayGreeting.java)

[Example 2](app/src/main/java/com/example/solidprinciplesandroid/lsp/AreaManager.java)

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

- First introduced in paper **A Behavioral Notion of Subtyping (1994)** by Barbara H. Liskov. [link](http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.39.1223)
- A subclass should override the parent class’ methods in a way that does not break functionality from a client’s point of view. Here is a simple example to demonstrate the concept.  

[Example 1](app/src/main/java/com/example/solidprinciplesandroid/lsp/LSP.java)

[Example 2](app/src/main/java/com/example/solidprinciplesandroid/lsp/AndroidExample.java)
 
### 4. Interface Segregation Principle (ISP)
 
 >The interface-segregation principle (ISP) states that no client should be forced to depend on methods it does not use.
 
 In other words
 
 > Make fine grained interfaces that are client-specific.
 
 Again in other words
 
 >Many client-specific interfaces are better than one general purpose interface
 
 - This principle states that once an interface becomes too fat, it needs to be split into smaller interfaces so that client of the interface will only know about the methods that pertain to them. As you know, the Android View class is the root superclass for all Android views. You name it, if it’s a Button, the root superclass is View.
 
 [Example](app/src/main/java/com/example/solidprinciplesandroid/isp/ISP.java)
 
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
 **6.** [https://stackoverflow.com/questions/56860/what-is-an-example-of-the-liskov-substitution-principle](https://stackoverflow.com/questions/56860/what-is-an-example-of-the-liskov-substitution-principle)
