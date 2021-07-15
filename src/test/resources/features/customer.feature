Feature: Customer API
  Scenario: Create Customer
    Given Set POST customer api endpoint
    When Set request HEADER
    And Send a POST HTTP request
    Then Receive status code of 200

  Scenario: Delete customer
    Given Set DELETE custowe
    When Delete this customer
    Then revice a message "Delete complete"