# spring-boot-social-login
In this tutorial, we’ll focus on adding a new Facebook login to an existing form-login app.

We’re going to be using the Spring Social support to interact with Facebook and keep things clean and simple.


    we’re using a ProviderSignInController to enable the Facebook authentication
    By sending a POST to “/signin/facebook” – this controller will initiate a user sign-in using the Facebook service provider
    we’re setting up a SignInAdapter to handle the login logic in our application
    And we also setting up a ConnectionSignUp to handle signing up users implicitly when they first authenticate with Facebook.
    And for using normal login send a request to /login
