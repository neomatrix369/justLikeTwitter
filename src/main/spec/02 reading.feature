Feature: Reading another user's timeline

  Scenario: I can view Alice's timeline
    Given I am at the JustLikeTwitter command prompt ">"
    And Alice's timeline contains the required posts
    When I type "Alice" at the prompt
    Then I see "I love the weather today (5 minutes ago)" at the prompt

  Scenario: I can view Bob's timeline
    Given I am at the JustLikeTwitter command prompt ">"
    And Bob's timeline contains the required posts
    When I type "Bob" at the prompt
    Then I see the below messages in the console:
         "Good game though. (1 minute ago)"
         "Damn! We lost! (2 minutes ago)"