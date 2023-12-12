Cypress.Commands.add(`signOut`, () => {
    cy.log(`sign out by clearing all cookies.`);
    cy.clearCookies({ domain: null });
});

Cypress.Commands.add("LoggingIn", () => {
    cy.log(`Starting to Log In.`);
    cy.visit(`http://localhost:3000/`);
    cy.window()
        .should((window) => {
            expect(window).to.not.have.property(`Clerk`, undefined);
            expect(window.Clerk.isReady()).to.eq(true);
        })
        .then(async (window) => {
            await cy.clearCookies({ domain: window.location.domain });
            const res = await window.Clerk.client.signIn.create({
                identifier: Cypress.env(`test_email`),
                password: Cypress.env(`test_password`),
            });
            await window.Clerk.setActive({
                session: res.createdSessionId,
            });
            cy.log(`Finished Logging In.`);
        });
});


