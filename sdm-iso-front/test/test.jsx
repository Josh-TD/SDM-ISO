const assert = require("assert").strict;
const webdriver = require("selenium-webdriver");
require("geckodriver");

// App Server & Current Title
const serverUrl = "http://localhost:3000/#";
const appTitle = "SDM File Manager";
const username = "sdmisoteam@gmail.com"
const password = "sdm-iso559"


// Chrome Browser Driver
const browser = new webdriver.Builder()
    .usingServer()
    .withCapabilities({browserName: "chrome"})
    .build();

// Get the title of our app
function logTitle() {
    return new Promise((resolve, reject) => {
        browser.getTitle().then(function(title) {
            resolve(title);
        });
    });
}

// Check if the value exists in the array or not
describe("Array", function() {
    describe("#indexOf()", function() {
        it("should return -1 when the value is not present", function() {
            assert.equal([1, 2, 3].indexOf(4), -1);
        });
    });
});

// Home Page Tests
describe("Home Page", function() {
    // Title Test
    it("Load Home Page and Get Title", function() {
        return new Promise((resolve, reject) => {
            browser
                .get(serverUrl)
                .then(logTitle)
                .then(title => {
                    assert.strictEqual(title, appTitle);
                    resolve();
                    console.log("Load Home Page and Get Title Passed")
                })
                .catch(err => reject(err));
        });
    });
    // after(function() {
    //     browser.quit();
    // });

    // Login Test
    describe("Login", function() {
        it("should log into the website", function() {
            return new Promise((resolve, reject) => {
                browser
                    .get(serverUrl)
                    .then(() => browser.findElement(webdriver.By.id("username")).sendKeys(username))
                    .then(() => browser.findElement(webdriver.By.id("password")).sendKeys(password))
                    .then(() => browser.findElement(webdriver.By.id("Sign In")).click())
                    .then(() => browser.wait(webdriver.until.urlIs("http://localhost:3000/protected")))
                    .then(() => resolve())
                    .catch(err => reject(err));
            });
        });
        // after(function() {
        //     browser.quit();
        // });
    });

    describe("File Viewer bmp", function() {
        it("should successfully view bmp file", function() {
            return new Promise((resolve, reject) => {
                browser
                    .findElement(webdriver.By.id("row-3"))
                    .then(() => browser.findElement(webdriver.By.id("row-3")).click())
                    .then(() => browser.findElement(webdriver.By.id("modal-download-button")))
                    .then(() => browser.findElement(webdriver.By.id("Sunrise_Image_Test19.bmp")))
                    .then(() => browser.findElement(webdriver.By.id("modal-exit-button")))
                    .then(() => browser.findElement(webdriver.By.id("modal-exit-button")).click())
                    .then(() => resolve())
                    .catch(err => reject(err));
            });
        });
        // after(function() {
        //     browser.quit();
        // });
    });

    describe("File Viewer xlsm", function() {
        it("should successfully view xlsm file", function() {
            return new Promise((resolve, reject) => {
                browser
                    .findElement(webdriver.By.id("row-0"))
                    .then(() => browser.findElement(webdriver.By.id("row-0")).click())
                    .then(() => browser.findElement(webdriver.By.id("modal-download-button")))
                    .then(() => browser.findElement(webdriver.By.id("Sunrise_Image_Test19.bmp")))
                    .then(() => browser.findElement(webdriver.By.id("modal-exit-button")))
                    .then(() => browser.findElement(webdriver.By.id("modal-exit-button")).click())
                    .then(() => resolve())
                    .catch(err => reject(err));
            });
        });
        // after(function() {
        //     browser.quit();
        // });
    });
});