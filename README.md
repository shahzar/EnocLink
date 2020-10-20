### Architecture
This project uses MVVM architecture along with clean coding principles.

### Questions
1. What frameworks or supporting libraries did you use to make the task simpler and/or easier to
accomplish?

    The project uses Retrofit for handling WebService requests, Dagger2 for Dependency Injection & Coroutines for handling network operations asynchronously.
    It also uses imagepicker, for image selection & image size management after selection.

2. How did you ensure that the display of the avatar image (from a remote URL) gave the best user experience?

    Glide image loading framework is used to ensure best user experience. It uses memory and disk caching, to be less network dependent and more time efficient.

3. How did you set up the app so that it was automatically logged in for the user on subsequent
uses?

    Once logged in, the token is stored locally and validated on subsequent uses.

4. How did you cope with building the sample app without having access to the real back-end
API?

    The project uses a MockAPI layer over the actual API layer to provide the application with required data. 
    Once the API is available, it can easily be integrated by removing the mock layer.

5. What testing did you do, and why?

    Uses JUnit for Unit testing, to ensure proper functioning of the tested blocks.

