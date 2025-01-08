Feature: Memory Game
  Tests the main functionalities of the Jeu class.

  Scenario: Start a game with 3 pairs
    Given I create a game with 3 pairs
    Then the total number of cards should be 6
    And no card should be found
    And the game should not be finished

  Scenario: Play a valid move and find a pair
    Given I create a game with 2 pairs
    When I play the first card number 0
    And I play the second card with the same number 1
    Then I should have found a pair
    And the number of moves played should be 2

  Scenario: Play a move with an invalid card
    Given I create a game with 2 pairs
    When I play an invalid card with the number -1
    Then an error should be returned

