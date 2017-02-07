# SimpleDialogFragment
An Android library that provides a simple implementation of a DialogFragment.

Are you tired of creating a new DialogFragment for each Dialog that you want to display?

The current recommended way of displaying Dialogs in an Android application involves the creation of a subclass of a DialogFragment (official documentation can be found [here](https://developer.android.com/guide/topics/ui/dialogs.html)).
Creating a new DialogFragment subclass for each Dialog we need to display in an application is tedious and we all know that an application often contains a lot of these dialogs. This leads to lots of DialogFragment classes that provide no value to the project.
What if we could eliminate those?

This library is a simple implementation of a DialogFragment that allows you to specify the content of the Dialog, while still using the recommended DialogFragment and all its lifecycle benefits.

Example of use :

    SimpleDialogFragment.newInstance(
                SimpleDialogContent.builder()
                        .setTitle("My Title")
                        .setMessage("My Dialog Message")
                        .build())
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);


## Getting started

    dependencies {
        compile 'com.julienarzul:simpledialogfragment:1.0.2'
    }

## How to use
### Basic Use
The simplest way to use this library is to show a Dialog with a title, message and a positive button.

In order to do that, we simply need to create and show a new instance of the SimpleDialogFragment class. The instance created must be given a SimpleDialogContent object that will define the content of the Dialog shown.

The following example shows the code to use to display a dialog with a custom title, custom message and custom positive button text (this is the enclosing Activity or Fragment):

    SimpleDialogFragment.newInstance(
                SimpleDialogContent.builder()
                        .setTitle("My Title")
                        .setMessage("My Dialog Message")
                        .setPositiveButtonText("Got it")
                        .build())
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);

### Supported AlertDialog content
The SimpleDialogContent object given to the SimpleDialogFragment supports defining:
* a title
* a message
* a positive button
* a negative button
* a neutral button
* whether the Dialog should be cancelable or not

Any combination of these attributes can easily be created via the SimpleDialogContent.Builder.

It is mandatory to specify at least a non-null message to the dialog.

### Specify button listeners
More often than not, we need to implement some behaviour when the user has agreed (or disagreed) to the content of the Dialog. For that purpose, we need to add listeners to the SimpleDialogFragment. The Android documentation shows an example where the Activity containing the DialogFragment is the dialog's buttons listener ([here](https://developer.android.com/guide/topics/ui/dialogs.html#PassingEvents)). Implementing it that way allows the listener to be kept on activity lifecycle events (such as activity destruction/recreation).

This library uses the exact same principle. The enclosing activity must implement an interface if it want to listen to the dialog's buttons click events. The below example shows how to listen to the positive and negative buttons click events:

    public class MyActivity extends AppCompatActivity implements SimpleDialogFragment.OnPositiveButtonClickListener,
        SimpleDialogFragment.OnNegativeButtonClickListener

There are three interfaces that the activity can implement:
* SimpleDialogFragment.OnPositiveButtonClickListener
* SimpleDialogFragment.OnNegativeButtonClickListener
* SimpleDialogFragment.OnNeutralButtonClickListener

#### Particular case: Dialogs inside Fragments
When displaying a DialogFragment inside a Fragment, we probably would like to perform our Dialog click behavior directly in our Fragment and completely bypass the Activity.  
SimpleDialogFragment allows you to do that easily.

Implement the listener interfaces directly in your Fragment:

    public class NestedFragment extends Fragment implements SimpleDialogFragment.OnPositiveButtonClickListener,
        SimpleDialogFragment.OnNegativeButtonClickListener

and display the SimpleDialogFragment:

    SimpleDialogFragment dialogFragment = SimpleDialogFragment.newInstance(dialogContent);
        dialogFragment.setTargetFragment(this, SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE);
        dialogFragment.show(this.getChildFragmentManager(), SimpleDialogFragment.TAG);

**Warning:** Since we're nesting a fragment into another one, you must use the *getChildFragmentManager()* method to show the dialog.

#### Particular case: Displaying several SimpleDialogFragments
When displaying several SimpleDialogFragment in the same Activity (or Fragment), we need a way to know which dialog triggered a click on the buttons.  
To fix that problem, SimpleDialogFragment uses a Request Code, in the same manner than with onActivityResult.  

When creating a SimpleDialogContent object, you can give it a request code (or when setting the target fragment in case of a fragment): 

    SimpleDialogContent dialogContent = SimpleDialogContent.builder()
                .setMessage("Fire missiles?")
                .setPositiveButtonText("Fire")
                .setNegativeButtonText("Cancel")
                .setRequestCode(SIMPLE_DIALOG_FRAGMENT_MY_REQUEST_CODE)
                .build();

That request code is then passed to you in each of the click events triggered by the listeners:

    @Override
    public void onDialogPositiveButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_FRAGMENT_MY_REQUEST_CODE:
                    // TODO: Implement positive button behaviour
                    break;
            }
        }
    }

## Contributing
Any contributions is welcome through Pull Requests. Please take the time to clearly explain the feature you wish to add or the bug you're trying to fix.

Don't hesitate to raise an issue before opening a pull request so that we can discuss it before-hand.

## License

    Copyright 2017 Julien Arzul

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
