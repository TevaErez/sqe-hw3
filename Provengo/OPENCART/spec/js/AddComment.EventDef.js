/* @Provengo summon selenium */
// like stepfile

/**
 *  The findProduct event defines the selenium actions for getting to the product page of the product xpath that was given in the parameter e.
 */
defineEvent(SeleniumSession, "findProduct", function(session, e) {

  bp.log.info(e);

  // click on product
  session.click("/html/body/main/div[2]/div/div/div[2]/div[1]/form/div/div[1]/a/img");
})

/**
 * The goToComments event defines the selenium action for clicking the comments section in the product page.
 */
defineEvent(SeleniumSession, "goToComments", function(session, e) {

  // click on comment section
  session.click("/html/body/main/div[2]/div/div/ul/li[3]/a");

})

/**
 * The writeComment event defines the selenium action for entering a name and comment to the relevant text boxes and clicking a rating.
 */
defineEvent(SeleniumSession, "writeComment", function(session, e) {

  // write name
  session.writeText("/html/body/main/div[2]/div/div/div[2]/div[3]/form/div[2]/input", e.name);
  // write comment
  session.writeText("/html/body/main/div[2]/div/div/div[2]/div[3]/form/div[3]/textarea", e.comment);

  session.scrollToBottom("/html/body/main/div[2]/div/div/div[2]/div[3]/form/div[5]/div/button");
  session.sleep(1000)

  // pick rating
  session.click("/html/body/main/div[2]/div/div/div[2]/div[3]/form/div[4]/div[1]/input[5]");

  // post comment
  session.click("/html/body/main/div[2]/div/div/div[2]/div[3]/form/div[5]/div/button");

})

/**
 * The writeComment event defines the selenium action for entering a name and comment to the relevant text boxes and clicking a rating.
 */
defineEvent(SeleniumSession, "CheckAssert", function(session, e) {

  session.assertText("/html/body/main/div[2]/div/div/p", "Product not found!")

})

/**
 * The logInAsAdmin event defines the selenium action for entering a username and password to the relevant text boxes and logging in as admin.
 */
defineEvent(SeleniumSession, "logInAsAdmin", function(session, e) {

  // write username
  session.writeText("/html/body/div[1]/div[2]/div/div/div/div/div[2]/form/div[1]/div/input", e.username);
  // write password
  session.writeText("/html/body/div[1]/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/input", e.password);
  //
  // click login button
  session.click("/html/body/div[1]/div[2]/div/div/div/div/div[2]/form/div[3]/button");
})

/**
 * The goToProducts event defines the selenium action for navigating to the products managing page as admin.
 */
defineEvent(SeleniumSession, "goToProducts", function(session, e) {

  // click X button
  session.click("/html/body/div[1]/div[2]/div[3]/div/div/div[1]/button");

  // click menu button
  session.click("/html/body/div[1]/header/div/button");

  // click Catalog button
  session.click("/html/body/div[1]/nav/ul/li[2]/a");

  // click Products button
  session.click("/html/body/div[1]/nav/ul/li[2]/ul/li[2]/a");
})


/**
 * The deleteProduct event defines the selenium action for selecting and deleting a specific product.
 */
defineEvent(SeleniumSession, "deleteProduct", function(session, e) {

  // click tick mark of relevant product
  session.click("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div[2]/form/div[1]/table/tbody/tr[1]/td[1]/input");

  // click delete button
  // session.click("/html/body/div[1]/div[2]/div[1]/div/div/button[3]/i");
  // session.writeText("/html", "\n", false)
})