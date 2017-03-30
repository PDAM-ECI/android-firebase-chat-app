# Android Firebase Chat App

 Create a project on the Firebase console (https://console.firebase.google.com) and add the _google-services.json_ file to root of the project.

**Part 1: Authentication with Firebase**

1) Download the project and make sure it works properly.

2) Add the following Firebase dependencies to your build.gradle file:

 ```groovy
      compile 'com.google.firebase:firebase-core:10.2.0'
      compile 'com.google.firebase:firebase-database:10.2.0'
      compile 'com.google.firebase:firebase-auth:10.2.0'
      compile 'com.google.firebase:firebase-storage:10.2.0'
 ```
  
3) Create an activity called _LoginActivity_ that has a button that will do anonymous authentication with the Firebase service.

4) Create an instance of the Firebase authentication class:

 ```java
      FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
 ```

4) Make the _LoginActivity_ implement _FirebaseAuth.AuthStateListener_  and then override the following methods to add and remove the listener to the _FirebaseAuth_ instance:

 ```java
      @Override
      public void onStart()
      {
          super.onStart();
          firebaseAuth.addAuthStateListener( this );
      }
      
      @Override
      public void onStop()
      {
          super.onStop();
          firebaseAuth.removeAuthStateListener( this );
      }
 ```
 
5) Add the following logic to the onAuthStateChanged method in order to start the _MainActivity_ once the user is authenticated:
     
 ```java
     @Override
     public void onAuthStateChanged( @NonNull FirebaseAuth firebaseAuth )
     {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if ( user != null )
        {
          startActivity( new Intent( this, MainActivity.class ) );
          finish();
        }
     }
 ```     
    
**Part 2: Saving data to the Firebase Realtime Database**
    
1) Edit the layout of the _MainActivity_ with the following code:


 ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:app="http://schemas.android.com/apk/res-auto"
          xmlns:tools="http://schemas.android.com/tools"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:orientation="vertical"
          android:paddingBottom="@dimen/activity_vertical_margin"
          app:layout_behavior="@string/appbar_scrolling_view_behavior"
          tools:context=".ui.activity.MainActivity"
          tools:showIn="@layout/activity_main">
      
      
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"/>
      
        <LinearLayout
            android:id="@+id/messages_layout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
      
            android:orientation="vertical"
            android:padding="10dp">
      
          <EditText
              android:id="@+id/sender"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:layout_marginBottom="10dp"
              android:hint="@string/name" />
      
          <EditText
              android:id="@+id/message"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:layout_marginBottom="10dp"
              android:hint="@string/write_message" />
      
          <LinearLayout
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:orientation="horizontal">
      
            <Button
                android:id="@+id/add_picture"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:padding="10dp"
                android:text="@string/send_photo" />
      
            <Button
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:onClick="onSendClicked"
                android:text="@string/send_message" />
      
          </LinearLayout>
        </LinearLayout>
      
      
      </LinearLayout>

 ```


2) Create a Message java class that will contain a message String and a user String.
 
 
3) Create a database reference to the 'messages' node by adding the following code to the _MainActivity_:
 
 
 ```java
      FirebaseDatabase database = FirebaseDatabase.getInstance();
     
      FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
     
      DatabaseReference databaseReference = database.getReference( "messages" );
 ```
 
4) Create an _onClick_ event listener for the Send Message button and inside create an instance of the _Message_ class and then send this object to the firebase database as follows:

```java
      databaseReference.push().setValue( message );
 ```

5) Verify on the Firebase console under the database option that the node is store as expected.


**Part 3: Reading data to the Firebase Realtime Database**

6) Create a _ChildEventListener_ on the MainActivity and add this to the database reference:
 ```java
       databaseReference.addChildEventListener( messagesListener );
  ```
 
7) Uncomment the _MessagesAdapter_ class and make sure it compiles properly.
 
8) Create an instance of the _MessagesAdapter_ and add it to the _RecyclerView_ that is defined in the layout after configuring it:

```java
       recyclerView.setHasFixedSize( true );
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
       linearLayoutManager.setReverseLayout( true );
       recyclerView.setLayoutManager( linearLayoutManager );
       recyclerView.setAdapter( messagesAdapter );
  ```

9) Add the following method to the _MainActivity_ and called it on the _onChildAdded_ and _onChildChanged_ methods of the _ChildEventListener_.

```java
   private void updateMessage( DataSnapshot dataSnapshot )
       {
           final Message message = dataSnapshot.getValue( Message.class );
           if ( message != null )
           {
               runOnUiThread( new Runnable()
               {
                   @Override
                   public void run()
                   {
                       messagesAdapter.addMessage( message );
                   }
               } );
           }
       }
   
  ```
  
  
 10) Call the removeMessage method on the onChildRemoved method of the ChildEventListener:
 
  
```java
     @Override
      public void onChildRemoved( DataSnapshot dataSnapshot )
      {
          Message message = dataSnapshot.getValue( Message.class );
          if ( message != null )
          {
              messagesAdapter.removeMessage( message );
          }
      }

  ```
  
  
11) Add messages from the form and verify that the messages are rendered properly.
  
  
12) Remove a message from the Firebase console and verify that message is removed from the list correctly.