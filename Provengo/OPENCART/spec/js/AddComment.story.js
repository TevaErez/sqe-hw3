/* @provengo summon selenium */
// like feature file


 /** This story opens a new browser window, goes to http://localhost/opencartpro/, and adds a comment for a product.
 */
story('AddCommentToProduct', function () {
  let s = new SeleniumSession().start('http://localhost/opencartpro/')
  s.findProduct()
  s.goToComments()
  s.writeComment({ name: 'Ofer', comment: 'this product is amazing love it' })
})

/**
 * This story opens a new browser window, goes to http://localhost/opencartpro/admin, and deletes a product. */

story('deleteProductbyAdmin', function () {
  // the "with" statement makes it redundant to write "s." before each call to a defined event (like the story above)
  with (new SeleniumSession().start('http://localhost/opencartpro/admin/')) {
    logInAsAdmin({username: 'admin', password: '3322'})
    goToProducts()
    deleteProduct()
  }
})


/**
 * This story opens a new browser window, goes to 'http://localhost/opencartpro/', and checks if a product was deleted. */

story('AssertDeletion', function () {
  on(Any('EndOfAction').and(Any({eventName: "deleteProduct"})), function () {
    // the "with" statement makes it redundant to write "s." before each call to a defined event (like the story above)
    with (new SeleniumSession("three").start('http://localhost/opencartpro/index.php?route=product/product&language=en-gb&path=18&product_id=46')) {
      Assert({str: "Product not found!"})
    }
  })
})