###Exercise

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below 
(rest of the info available separately).

####Using ApprovalTests library

```FullLifeCycleAcceptanceTest test``` has been implemented using [http://blog.approvaltests.com/](http://blog.approvaltests.com/).
Also see maven implementation example ([pom.xml](https://github.com/mzagar/approvals-example/blob/master/pom.xml)).

Previously used [http://approval.readthedocs.org/en/latest/getting-started.html](http://approval.readthedocs.org/en/latest/getting-started.html).            

####Usage

    $ sh runJustLikeTwitter.sh

                or

    $ mvn exec:java -Dexec.mainClass="com.codurance.JustLikeTwitter"


Alternatively you can also run from within the IDE:
    
Load the class ```com.codurance.JustLikeTwitter``` and run the ```main()``` function. Then wait for the app to load in the console tab of your IDE.

####Expected UI

    ---------------------------------------------------------------------------------------
    Running JustLikeTwitter console app
    Use Ctrl-C to exit the program.
    Only sunny day scenarios have been covered.
    ---------------------------------------------------------------------------------------

    Command-line help (usage with examples):

    - Posting to personal timeline: <user name> -> <message>
    for e.g.
        > Alice -> I'm having a great time

    - Reading any user's timeline: <user name>
    for e.g.
        > Alice
        I'm having a great time (2 seconds ago)

    - Following another user: <user name> follows <another user>
    for e.g.
        > Alice follows Bob

    - Display user's wall : <user name> wall
    for e.g.
        > Alice wall
        Bob - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
        Alice - I'm having a great time (5 minutes ago)

    >
    
####Some build commands

    $ mvn clean package

    $ mvn test
    
When running the above commands, and when the ```FullLifeCycleAcceptanceTest``` is run, ```ApprovalTests``` will ask if you want to validate your first output, press OK in the dialog. 