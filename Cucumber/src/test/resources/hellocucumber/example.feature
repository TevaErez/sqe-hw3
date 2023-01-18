Feature: A set of scenarios for testing review section in opencart module

  Scenario Outline: User fills a review on product
    Given user in home page
    And <ProductName> created
    And <ProductName> featured in home page
    When navigate to review section of <ProductName> featured
    And submit review with <ReviewDetails> details
    Then new comment added
    And close
    Examples:
      | ProductName | ReviewDetails |
      | "product1"  |"name1,review for first person for product,5" |
      | "product2"  |"name2,review for second person for product,3" |

  Scenario Outline: Admin deletes product
    Given admin in Login page that is linked to product list
    And <ProductName> created
    When navigate to product <ProductName>
    And deletes <ProductName>
    Then product deleted
    And close
    Examples:
      | ProductName |
      | "product1"   |
      | "something4"  |

  Scenario Outline: two above combined
    Given user in home page
    And <ProductName> created
    And <ProductName> featured in home page
    When navigate to review section of <ProductName> featured
    And submit review with <ReviewDetails> details
    Given admin in Login page that is linked to product list
    And <ProductName> created
    When navigate to product <ProductName>
    And deletes <ProductName>
    Then product deleted
    And switch
    Then new comment added
    And close
    Examples:
      | ProductName | ReviewDetails |
      | "product1"   | "name1,review for first person for product,5" |

