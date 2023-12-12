const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
  env: {
    TEST_USER_EMAIL: "niya+clerk_test@test.com",
    TEST_USER_PASSWORD: "12345678",
    TEST_PHONE_NUMBER: "+12015550100",
    INIT_URL: "localhost:3000/"
  },
});