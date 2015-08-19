Feature: Displaying a user's wall

  Scenario: Charlie can subscribe to Alice’s timeline, and views an aggregated list of all subscriptions
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Alice's timeline contains the required posts
    And he enters "Charlie -> I'm in New York today! Anyone want to have a coffee?" at the prompt
    And then he enters "Charlie follows Alice" at the prompt
    When he types "Charlie wall" at the prompt
    Then he sees the below in the console
    "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
     Alice - I love the weather today (5 minutes ago)"

  Scenario: Charlie can subscribe to Bob’s timeline, and views an aggregated list of all subscriptions
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Bob's timeline contains the required posts
    And he enters "Charlie -> I'm in New York today! Anyone want to have a coffee?" at the prompt
    And then he enters "Charlie follows Bob" at the prompt
    When he types "Charlie wall" at the prompt
    Then he sees the below in the console
    "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
    Bob - Good game though. (1 minute ago)
    Bob - Damn! We lost! (2 minutes ago)"

  Scenario: Charlie can subscribe to Alice's and Bob’s timelines, and views an aggregated list of all subscriptions
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Alice's timeline contains the required posts
    And Bob's timeline contains the required posts
    And he enters "Charlie -> I'm in New York today! Anyone want to have a coffee?" at the prompt
    And then he enters "Charlie follows Alice" at the prompt
    And then he enters "Charlie follows Bob" at the prompt
    When he types "Charlie wall" at the prompt
    Then he sees the below in the console
    "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
    Bob - Good game though. (1 minute ago)
    Bob - Damn! We lost! (2 minutes ago)
    Alice - I love the weather today (5 minutes ago)"