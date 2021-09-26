Feature: Amazon Search and Cart feature

  @wip
  Scenario: Amazon search and cart functionality
    Given User is on the Amazon home page
    When User searches for hats for men
    And User adds the first hat from results to cart with quantity two
    Then The total price and quantity should be correct
    When User reduces the quantity from two to one
    Then Total price and quantity has been correctly changed