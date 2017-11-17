@smoketest
Feature: Technical task for GrandParade. Open WilliamHill and place bet.

  Scenario Outline: Open WilliamHill and place bet to Premier League event.
    Given open page "BettingPage"
    And login with user "WHATA_FOG3" and password "F0gUaTtest"
    And navigate to football championship "English Premier League"
    When bet on "first" event
    And bet for "home team"
    And bet amount "<bet amount>"
    Then bet return amount is "<bet return>"
    And total bet return amount is "<total bet return>"
    And total stake amount is "<total stake>"
    And bet is placed after 'place bet' button is clicked.

    Examples:
      | bet amount | bet return | total bet return | total stake |
      | 0.05       | £0.11      | £ 0.11           | £ 0.05      |
      | 0.11       | £0.26      | £ 0.26           | £ 0.11      |
