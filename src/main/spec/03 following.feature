Feature: Following another user

  Scenario: Charlie can subscribe to Alice’s timeline
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Alice exists
    When he enters "Charlie follows Alice" at the prompt
    Then Alice is added to Charlie's follows list

  Scenario: Charlie can subscribe to Bob’s timeline
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Bob exists
    When he enters "Charlie follows Bob" at the prompt
    Then Bob is added to Charlie's follows list

  Scenario: Charlie can subscribe to Alice's and Bob’s timelines
    Given Charlie is at the JustLikeTwitter command prompt ">"
    And Alice exists
    And Bob exists
    When he enters "Charlie follows Alice" at the prompt
    And he enters "Charlie follows Bob" at the prompt
    Then Alice and Bob are added to Charlie's follows list