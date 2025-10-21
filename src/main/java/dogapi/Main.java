package dogapi;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String breed = (args.length > 0) ? args[0] : "hound";
        BreedFetcher fetcher = new CachingBreedFetcher(new DogApiBreedFetcher());
        try {
            int count = getNumberOfSubBreeds(breed, fetcher);
            System.out.println(breed + " has " + count + " sub-breeds.");
        } catch (BreedFetcher.BreedNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Return the number of sub breeds that the given dog breed has according to the
     * provided fetcher.
     * @param breed the name of the dog breed
     * @param breedFetcher the breedFetcher to use
     * @return the number of sub breeds. Zero should be returned if there are no sub breeds
     * returned by the fetcher
     */
    public static int getNumberOfSubBreeds(String breed, BreedFetcher breedFetcher) throws BreedFetcher.BreedNotFoundException {
        // TODO Task 3 implement this code so that it is entirely consistent with its provided documentation.
        // return statement included so that the starter code can compile and run.
        List<String> subBreeds = breedFetcher.getSubBreeds(breed);
        return subBreeds.size();
    }
}