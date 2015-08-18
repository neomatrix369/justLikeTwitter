Feature: Posting to a personal timeline

  Scenario: Alice can publish a message to a personal timeline
    Given Alice is at the TwitterLike command prompt ">"
    And Alice's timeline is empty
    When Alice types "Alice -> I love the weather today" at the prompt
    Then the message is added to Alice's timeline

  Scenario: Bob can publish multiple messages to a personal timeline
    Given Bob is at the TwitterLike command prompt ">"
    And Bob's timeline is empty
    When Bob types "Bob -> Damn! We lost!" at the prompt
    And then types "Bob -> Good game though."  at the prompt
    Then the messages are added to Bob's timeline, in reverse order