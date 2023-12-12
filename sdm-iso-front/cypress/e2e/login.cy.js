describe("Inital Login Page (not logged in)", () => {
  it("Inital Login Screen", () => {
    cy.visit("http://localhost:3000/");
    // cy.get("h1").contains("Signed out");
  });
});

describe("Logging Inc + Main Page ", () => {
  beforeEach(() => {
    cy.session('logged-in', () => {
      cy.LoggingIn();
    });
  });

  it("Navigate to File Manager", () => {
    cy.visit("http://localhost:3000/protected");
    cy.get("button").contains("Apply Filters");
    cy.get("button").contains("Clear Filters");
    cy.get("table").contains("File Name");
    cy.get("table").contains("Project Name");
    cy.get("table").contains("Customer");
    cy.get("table").contains("Date Created");
    cy.get("table").contains("Descriptions");
  });

  it("Apply One Filter", () => {
    cy.get("h2").contains("Project Type");
    cy.get("svg-cursor-pointer").click();
    cy.get("h2").contains("Incremental Capacity").click();
    cy.get("button").contains("Apply Filters").click();
    cy.get("table").contains("Lightening Power");
    cy.get("table").contains("Gravity Works");
  });

  it("Apply Multiple Filters", () => {
    cy.get("h2").contains("Project Type");
    cy.get("svg-cursor-pointer").click();
    cy.get("h2").contains("Incremental Capacity").click();
    cy.get("h2").contains("File Type");
    cy.get("svg-cursor-pointer").click();
    cy.get("h2").contains("BMP").click();
    cy.get("h2").contains("PDF").click();
    cy.get("button").contains("Apply Filters").click();
    cy.get("table").contains("Lightening Power");
    cy.get("table").contains("Gravity Works");
    cy.get("table").contains(".bmp");
    cy.get("table").contains(".bmp");
  });

  it("Use Search", () => {
    cy.get("h3").contains("Search by");
    cy.get("menu").contains("Select...").click();
    cy.get("option").contains("Customer Name").click();
    cy.get("search-bar").contains("SEARCH")
        .type("New Contract Year");
    cy.get("img").contains("search-icon").click();
    cy.get("table").contains("Project Name")
        .contains("New Contract Year");
  });

  it("Use Advanced Search", () => {
    cy.get("h4").contains("Advanced Search").click();
    cy.get("textarea").contains("Customer Name:")
        .type("Energy");
    cy.get("textarea").contains("File Description:")
        .type("Resource");
    cy.get("button").contains("Search").click();
    cy.get("table").contains("Customer Name")
        .contains("Energy Importer");
    cy.get("table").contains("Descriptions")
        .contains("Resource energy notes")
        .contains("Guidelines for resource allocation")
        .contains("Sponsored Policy Resource Certification");
  });

});