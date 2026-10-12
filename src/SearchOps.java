public class SearchOps {
    public static class Result {
        public final int index;
        public final long steps;
        public final long nanos;

        public Result(int index, long steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }

        public boolean found() {
            return index >= 0;
        }
    }

    public static Result linear(int[] data, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int found = -1;
        for (int i = 0; i < data.length; i++) {
            steps++;
            if (data[i] == target) {
                found = i;
                break;
            }
        }
        return new Result(found, steps, System.nanoTime() - start);
    }

    public static Result binary(int[] sortedData, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int found = -1;
        int low = 0;
        int high = sortedData.length - 1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (sortedData[mid] == target) {
                found = mid;
                break;
            } else if (sortedData[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new Result(found, steps, System.nanoTime() - start);
    }
}
