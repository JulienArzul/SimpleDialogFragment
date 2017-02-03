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
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .build())
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);


## Getting started
// TODO : upload library to jcenter

## How to use
// TODO : Proper doc of the different cases (Builder + listeners)

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
