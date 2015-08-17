Feature: Following another user

  Scenario: Charlie can subscribe to Alice’s timeline
    Given Charlie is at the TwitterLike command prompt ">"
    And Alice's exists
    When he enters "Charlie follows Alice" at the prompt
    Then Alice is added to Charlie's follows list

  Scenario: Charlie can subscribe to Bob’s timeline
    Given Charlie is at the TwitterLike command prompt ">"
    And Bob's exists
    When he enters "Charlie follows Bob" at the prompt
    Then Bob is added to Charlie's follows list

  Scenario: Charlie can subscribe to Alice's and Bob’s timelines
    Given Charlie is at the TwitterLike command prompt ">"
    And Alice's exists
    And Bob's exists
    When he enters "Charlie follows Alice" at the prompt
    And he enters "Charlie follows Bob" at the prompt
    Then Alice and Bob are added to Charlie's follows list