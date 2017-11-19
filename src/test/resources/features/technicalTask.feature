@SmokeTest
Feature: Technical task for GrandParade. Open WilliamHill and place bet.

  Scenario Outline: Open WilliamHill and place bet to Premier League event.
    Given open page "BettingPage"
    And login with user "WHATA_FOG3" and password "F0gUaTtest"
    And navigate to football championship "English Premier League"
    When bet on "first" event
    And bet for "home team"
    And bet amount "<bet amount>"
    Then validate bet amount
    And validate total bet amount
    And validate total stake amount
    And bet is placed after 'place bet' button is clicked.

    Examples:
      | bet amount |
      | 0.05       |
      | 0.11       |

