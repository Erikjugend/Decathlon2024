package common;

public class Competitor {
    private String name;
    private int[] scores = new int[17];  // Store scores for 17 events

    public Competitor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(String discipline, int score) {
        switch (discipline) {
            // Decathlon Events
            case "100m (Decathlon)":
                scores[0] = score;
                break;
            case "400m (Decathlon)":
                scores[1] = score;
                break;
            case "1500m (Decathlon)":
                scores[2] = score;
                break;
            case "110m Hurdles (Decathlon)":
                scores[3] = score;
                break;
            case "Long Jump (Decathlon)":
                scores[4] = score;
                break;
            case "High Jump (Decathlon)":
                scores[5] = score;
                break;
            case "Pole Vault (Decathlon)":
                scores[6] = score;
                break;
            case "Discus Throw (Decathlon)":
                scores[7] = score;
                break;
            case "Javelin Throw (Decathlon)":
                scores[8] = score;
                break;
            case "Shot Put (Decathlon)":
                scores[9] = score;
                break;

            // Heptathlon Events
            case "100m Hurdles (Heptathlon)":
                scores[10] = score;
                break;
            case "200m (Heptathlon)":
                scores[11] = score;
                break;
            case "800m (Heptathlon)":
                scores[12] = score;
                break;
            case "High Jump (Heptathlon)":
                scores[13] = score;
                break;
            case "Long Jump (Heptathlon)":
                scores[14] = score;
                break;
            case "Shot Put (Heptathlon)":
                scores[15] = score;
                break;
            case "Javelin Throw (Heptathlon)":
                scores[16] = score;
                break;
        }
    }

    public Object[] getRowData() {
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }

        Object[] rowData = new Object[scores.length + 2];
        rowData[0] = name;
        System.arraycopy(scores, 0, rowData, 1, scores.length);
        rowData[17] = totalScore;  // Total score in the last column

        return rowData;
    }
}

