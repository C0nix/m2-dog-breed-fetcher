package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class

    private int callsMade;
    private final BreedFetcher fetcher;
    private final Map<String, List<String>> cache;
    public CachingBreedFetcher(BreedFetcher fetcher) {
    this.fetcher = fetcher;
    this.cache = new HashMap<>();
    this.callsMade = 0;
    }

    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException {
        // return statement included so that the starter code can compile and run.
        if (cache.containsKey(breed)) {
            return cache.get(breed);
        }

        // If not in cache, fetch from the underlying fetcher
        // Increment counter before making the call (counts even if exception thrown)
        callsMade++;
        List<String> subBreeds = fetcher.getSubBreeds(breed);

        // Store the result in the cache before returning
        cache.put(breed, subBreeds);

        return subBreeds;
    }

    public int getCallsMade() {
        return callsMade;
    }
}