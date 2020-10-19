Feature: Basic addition of two integers

  The calculator offers basic addition of two integers and supports negative numbers
  and positive numbers. The result should be the arithmetic addition of the two number numbers

  Scenario Outline: Addition
    Given The user opened the Calculator web page
    And The input area is empty
    When The user enters <number1> and <number2> and press Enter key
    Then The input area should display the <answer>

    Examples:
      |number1|number2|answer|
      |1      |2      |3     |
      |-1     |-2     |-3    |
      |-2     |4      |2     |
      |0      |0      |0     |
