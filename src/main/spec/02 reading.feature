Feature: Reading another user's timeline

  Scenario:  I can view Alice's timelines
    Given I am at the JustLikeTwitter command prompt ">"
    And Alice's timeline contains the required posts
    When I type "Alice" at the prompt
    Then I see "I love the weather today (5 minutes ago)" at the prompt

  Scenario: I can view Bob's timelines
    Given I am at the JustLikeTwitter command prompt ">"
    And Bob's timeline contains the required posts
    When I type "Bob" at the prompt
    Then I see the below messages in the console:
         "Good game though. (1 minute ago)"
         "Damn! We lost! (2 minutes ago)"

  Scenario: I can view anyone's timelines and compare timestamps
    Given I am at the JustLikeTwitter command prompt ">"
    When Alice types "Alice -> I love the weather today" at the console
    And I wait for 5 minutes
    And type "Alice" at the prompt
    Then Alice's timeline contains one message, marked posted "5 minutes ago" at the console

    When Bob types "Bob -> Damn! We lost!" at the console
    And wait for 1 minute
    And then type "Bob" at the prompt
    Then Bob's time line with one message, marked posted "1 minute ago" at the console
    And a minute later Bob types "Good game though." at the console
    And I wait for 1 minute
    And type "Bob" at the prompt
    Then I see two messages, in the order of last message first, with timestamps: "1 minute ago" and "2 minutes ago" at the console