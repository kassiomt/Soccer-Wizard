package kassio.service;

import kassio.resolvers.FeedforwardAlgorithm;

import static kassio.GlobalData.bias;

public class RunService {

    public static FeedforwardAlgorithm xorFeedforwardAlgorithm() {
        return new FeedforwardAlgorithm(bias);
    }
}
