###Exercise

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below 
(rest of the info available separately).

####Usage

    $ sh runJustLikeTwitter.sh

                or

    $ mvn exec:java -Dexec.mainClass="interfaces.JustLikeTwitter"


Alternatively you can also run from within the IDE:
    
    Load the class ```interfaces.JustLikeTwitter``` and run the ```main()``` function

Then wait for the app to load in the console tab of your IDE.

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