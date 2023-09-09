package com.example;

public class StatisticsWriter {

    public static void writeStatisticsToFiles(Stats stats) {
        String commanderCountFile = "commanderCount.json";
        String partnerPairCountFile = "partnerPairCount.json";
        String companionCountFile = "companionCount.json";
        String mainboardCountFile = "mainboardCount.json";
        String sideboardCountFile = "sideboardCount.json";
        String colourCountFile = "colourCount.json";

        String commanders = stats.getCommanders();

        if (commanders != null) {
            Writer.writeMapToFile(stats.getMainboardCount(), commanders + "_" + mainboardCountFile);
            Writer.writeMapToFile(stats.getSideboardCount(), commanders + "_" + sideboardCountFile);
        } else {
            Writer.writeMapToFile(stats.getColourCount(), colourCountFile);
            Writer.writeMapToFile(stats.getCommanderCount(), commanderCountFile);
            Writer.writeMapToFile(stats.getCompanionCount(), companionCountFile);
            Writer.writeMapToFile(stats.getMainboardCount(), mainboardCountFile);
            Writer.writeMapToFile(stats.getSideboardCount(), sideboardCountFile);
            Writer.writeMapToFile(stats.getPartnerPairCount(), partnerPairCountFile);
        }
    }
}
